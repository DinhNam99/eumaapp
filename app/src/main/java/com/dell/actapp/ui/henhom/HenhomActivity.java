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
import com.dell.actapp.adapter.AdapterHenhom;
import com.dell.actapp.model.Henhom;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HenhomActivity extends AppCompatActivity implements HenhomView,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rc_henhom)
    RecyclerView rc_henhom;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog = null;
    private LoadingCommon commonUtils;
    HenhomPresenter henhomPresenter;
    Utils utils;
    AdapterHenhom adapterHenhom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_henhom);
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

        henhomPresenter = new HenhomPresenter(this);
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
        tvTitle.setText("Danh sách hệ nhôm");

    }

    public void setUpRecyclerView(){
        rc_henhom.setHasFixedSize(true);
        rc_henhom.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        henhomPresenter.display(utils.getUrl());

    }
    @Override
    public void displayHenhom(ArrayList<Henhom> henhomArrayList) {
        Intent intent = getIntent();
        String id_ct = intent.getStringExtra("id_ct");
        swipeRefreshLayout.setRefreshing(false);
        adapterHenhom = new AdapterHenhom(HenhomActivity.this,henhomArrayList,id_ct);
        rc_henhom.setAdapter(adapterHenhom);

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
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        henhomPresenter.display(utils.getUrl());
    }
}
