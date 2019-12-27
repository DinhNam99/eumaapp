package com.dell.actapp.ui.cua;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dell.actapp.R;
import com.dell.actapp.adapter.AdapterCua;
import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Cua;
import com.dell.actapp.model.Cuadata;
import com.dell.actapp.network.source.AuthData;
import com.dell.actapp.ui.henhom.HenhomActivity;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CuaActivity extends AppCompatActivity implements CuaView,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTenct)
    TextView tvTenct;
    @BindView(R.id.rc_cua)
    RecyclerView rc_cua;
    @BindView(R.id.fbAdd)
    FloatingActionButton fbAdd;
    @BindView(R.id.tvNot)
    TextView tvNot;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterCua adapterCua;
    CuaPresenter cuaPresenter;
    Intent intent;
    Utils utils;
    ProgressDialog progressDialog = null;
    private LoadingCommon commonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cua);
        ButterKnife.bind(this);
        cuaPresenter = new CuaPresenter(this);
        utils = new Utils(getApplicationContext());
        commonUtils = new LoadingCommon(this,progressDialog);
        intent = getIntent();
        setUpRecyclerView();
        init();
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

        String title = intent.getStringExtra("tenct");
        Log.e("ten",title);
        tvTenct.setText(title);

    }

    public void setUpRecyclerView() {

        rc_cua.setLayoutManager(new GridLayoutManager(this, 4));
        String id_ct = intent.getStringExtra("id_ct");
        Log.e("id",id_ct);
        if(id_ct!=null) {
            cuaPresenter.display(utils.getUrl(), id_ct);
        }
        // SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

    }


    @OnClick(R.id.fbAdd)
    public void clickAddCua(){
        String id_ct = intent.getStringExtra("id_ct");
        Intent intent = new Intent(getApplicationContext(), HenhomActivity.class);
        intent.putExtra("id_ct",id_ct);
        startActivity(intent);
        finish();
    }

    @Override
    public void displayCuabyCt(ArrayList<Cua> cuaArrayList) {
        if(cuaArrayList.size()==0){
            tvNot.setVisibility(View.VISIBLE);
        }else{
            tvNot.setVisibility(View.INVISIBLE);
        }
        adapterCua = new AdapterCua(this, cuaArrayList);
        rc_cua.setAdapter(adapterCua);
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

    @Override
    public void displayCuadata(ArrayList<Cuadata> cuadataArrayList) {

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        String id_ct = intent.getStringExtra("id_ct");
        Log.e("id",id_ct);
        if(id_ct!=null) {
            cuaPresenter.display(utils.getUrl(), id_ct);
        }
    }
}
