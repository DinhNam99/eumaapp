package com.dell.actapp.ui.henhom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dell.actapp.R;
import com.dell.actapp.adapter.AdapterDetailLoaihn;
import com.dell.actapp.adapter.AdapterLoaihn;
import com.dell.actapp.model.Loaicua;
import com.dell.actapp.network.api.APILoaicua;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailLoaihnActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rc_detailloaihn)
    RecyclerView rc_detailloaihn;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    Utils utils;
    AdapterDetailLoaihn adapterDetailLoaihn;
    int id_henhom;
    String loaihn;
    ProgressDialog progressDialog = null;
    private LoadingCommon commonUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_loaihn);
        ButterKnife.bind(this);
        init();
        setUpRecyclerView();
        Intent intent = getIntent();
        String id_ct = intent.getStringExtra("id_ct");
        Log.e("id",id_ct);
    }
    public void init() {
        // SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        commonUtils = new LoadingCommon(this,progressDialog);
        utils = new Utils(getApplicationContext());
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("");
        actionbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("Loại cửa");
        Intent intent = getIntent();
        id_henhom = Integer.parseInt(intent.getStringExtra("id_henhom"));
        loaihn = intent.getStringExtra("id_loaihn");
    }

    public void setUpRecyclerView(){
        rc_detailloaihn.setHasFixedSize(true);
        rc_detailloaihn.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        displayLoaihn(id_henhom,loaihn);
    }

    private void displayLoaihn(int id_henhom,String loaihn) {
        Intent intent = getIntent();
        String id_ct = intent.getStringExtra("id_ct");
        commonUtils.showLoadingDialog();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(utils.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APILoaicua apiLoaicua = retrofit.create(APILoaicua.class);
        Call<List<Loaicua>> call = apiLoaicua.getLoaiCua(id_henhom,loaihn);
        call.enqueue(new Callback<List<Loaicua>>() {
            @Override
            public void onResponse(Call<List<Loaicua>> call, Response<List<Loaicua>> response) {
                ArrayList<Loaicua> loaicuaList = (ArrayList<Loaicua>) response.body();
                adapterDetailLoaihn = new AdapterDetailLoaihn(DetailLoaihnActivity.this, loaicuaList,id_ct);
                rc_detailloaihn.setAdapter(adapterDetailLoaihn);
                commonUtils.HideLoadingDialog();
            }

            @Override
            public void onFailure(Call<List<Loaicua>> call, Throwable t) {
                utils.showToast(t.getMessage());
                commonUtils.HideLoadingDialog();
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        displayLoaihn(id_henhom,loaihn);
    }
}
