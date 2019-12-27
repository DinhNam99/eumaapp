package com.dell.actapp.fragment.congtrinh;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dell.actapp.R;
import com.dell.actapp.adapter.AdapterCongTrinh;
import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.network.source.AuthData;
import com.dell.actapp.ui.cua.CuaActivity;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_ds_congtrinh extends Fragment implements CongtrinhView,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.fbAdd)
    FloatingActionButton add;
    @BindView(R.id.rc_congtrinh)
    RecyclerView rc_congtrinh;
    @BindView(R.id.tvNot)
    TextView tvNot;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    CongtrinhPresenter congtrinhPresenter;
    AdapterCongTrinh adapterCongTrinh;
    ProgressDialog progressDialog = null;
    private LoadingCommon commonUtils;
    ArrayList<CongTrinh> congTrinhs = new ArrayList<>();
    String id_ctthem;
    Utils utils;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ds_congtrinh, container, false);
        ButterKnife.bind(this, view);
        setUpRecyclerView();

        // SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        congtrinhPresenter = new CongtrinhPresenter(this);
        commonUtils = new LoadingCommon(getContext(), progressDialog);
        utils = new Utils(getContext());
    }

    public void setUpRecyclerView(){
        rc_congtrinh.setHasFixedSize(true);
        rc_congtrinh.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @OnClick(R.id.fbAdd)
    public void addClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(customLayout);
        builder.setTitle("Thêm công trình");
        EditText edTen = (EditText) customLayout.findViewById(R.id.tenctrinh);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                congtrinhPresenter.themCt(utils.getUrl(),edTen.getText().toString(),AuthData.getInstance().getUser().getId());

            }
        });
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alert1 = builder.create();
        alert1.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        congtrinhPresenter.displayCT(utils.getUrl());
    }

    @Override
    public void displayCongTrinh(ArrayList<CongTrinh> congTrinhList) {
        this.congTrinhs = congTrinhList;
        if(congTrinhList.size()==0){
            tvNot.setVisibility(View.VISIBLE);
        }else{
            tvNot.setVisibility(View.INVISIBLE);
        }
        adapterCongTrinh = new AdapterCongTrinh(getContext(),congTrinhList);
        rc_congtrinh.setAdapter(adapterCongTrinh);
        adapterCongTrinh.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void displayFailer(String text) {
        utils.showToast(text);
    }

    @Override
    public void themmoiThanhcong(String text, String tenct,String id_ct) {
        utils.showToast(text);
        adapterCongTrinh.notifyDataSetChanged();
        Intent intent = new Intent(getContext(), CuaActivity.class);
        intent.putExtra("id_ct",id_ct);
        intent.putExtra("tenct",tenct);
        Log.e("ten",tenct);
        startActivity(intent);
    }

    @Override
    public void themmoiThatbai(String text) {
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
        congtrinhPresenter.displayCT(utils.getUrl());
    }
}
