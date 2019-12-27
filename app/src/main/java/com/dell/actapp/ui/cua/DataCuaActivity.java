package com.dell.actapp.ui.cua;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.dell.actapp.adapter.AdapterCua;
import com.dell.actapp.adapter.AdapterCuadata;
import com.dell.actapp.model.Cua;
import com.dell.actapp.model.Cuadata;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataCuaActivity extends AppCompatActivity implements CuaView,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rc_cua)
    RecyclerView rc_cua;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterCuadata adapterCuadata;
    CuaPresenter cuaPresenter;
    Intent intent;
    Utils utils;
    ProgressDialog progressDialog = null;
    private LoadingCommon commonUtils;
    int id_henhom;
    String loaihenhom,loaicua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_cua);
        ButterKnife.bind(this);
        cuaPresenter = new CuaPresenter(this);
        utils = new Utils(getApplicationContext());
        commonUtils = new LoadingCommon(this,progressDialog);
        intent = getIntent();
        init();
        setUpRecyclerView();
        String id_ct = intent.getStringExtra("id_ct");
        Log.e("id",id_ct);
    }

    public void init() {
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
        id_henhom = Integer.parseInt(intent.getStringExtra("id_henhom"));
        loaihenhom = intent.getStringExtra("loaihenhom");
        loaicua = intent.getStringExtra("loaicua");
    }

    public void setUpRecyclerView() {

        rc_cua.setLayoutManager(new GridLayoutManager(this, 4));
        cuaPresenter.displayDataCua(utils.getUrl(),id_henhom,loaihenhom,loaicua);
        // SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        cuaPresenter.displayDataCua(utils.getUrl(),id_henhom,loaihenhom,loaicua);
    }

    @Override
    public void displayCuabyCt(ArrayList<Cua> cuaArrayList) {

    }

    @Override
    public void displayCuadata(ArrayList<Cuadata> cuadataArrayList) {
        Intent intent = getIntent();
        String id_ct = intent.getStringExtra("id_ct");
        adapterCuadata = new AdapterCuadata(DataCuaActivity.this,cuadataArrayList,id_ct);
        rc_cua.setAdapter(adapterCuadata);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayFailer(String text) {
        utils.showToast(text);
    }

    @Override
    public void showProgress() {
        commonUtils.showLoadingDialog();
    }

    @Override
    public void hidProgress() {
        commonUtils.HideLoadingDialog();
    }
}
