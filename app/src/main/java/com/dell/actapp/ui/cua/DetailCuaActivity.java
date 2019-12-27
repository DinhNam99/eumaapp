package com.dell.actapp.ui.cua;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dell.actapp.R;
import com.dell.actapp.adapter.AdapterCua;
import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Cua;
import com.dell.actapp.model.Cuadata;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailCuaActivity extends AppCompatActivity implements DetailCuaView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.edTencua)
    EditText edTenCua;
    @BindView(R.id.edRongcua)
    EditText edRongcua;
    @BindView(R.id.edCaocua)
    EditText edCaocua;
    @BindView(R.id.edHochancanh)
    EditText edHoechancanh;
    @BindView(R.id.edSobo)
    EditText edSobo;
    @BindView(R.id.image_cua)
    ImageView image_cua;
    @BindView(R.id.btnLuuCua)
    Button btnLuu;
    @BindView(R.id.btnQuaylai)
    Button btnQuaylai;
    Utils utils;
    ProgressDialog progressDialog = null;
    private LoadingCommon commonUtils;
    DetailCuaPresenter detailCuaPresenter;
    String anhcua, id_ct, id_cua,id_henhom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cua);
        ButterKnife.bind(this);
        utils = new Utils(getApplicationContext());
        commonUtils = new LoadingCommon(this,progressDialog);
        detailCuaPresenter = new DetailCuaPresenter(this);
        init();
        Intent intent = getIntent();
        String id_ct = intent.getStringExtra("id_ct");
        Log.e("id",id_ct);
    }
    public void init(){
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
        tvTitle.setText("Kích thước cửa");

        Intent intent = getIntent();
        int c = intent.getIntExtra("c",0);
        if(c == 1) {
            Cua cua = (Cua) intent.getSerializableExtra("cua");

            id_ct = cua.getIdCt();
            id_henhom = cua.getId_henhom();
            id_cua = cua.getIdCua();
            byte[] decodedString = Base64.decode(cua.getAnhcua(), Base64.DEFAULT);
            Bitmap imgBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            anhcua = cua.getAnhcua();
            image_cua.setImageBitmap(imgBitMap);
            edTenCua.setText(cua.getTencua());
            edRongcua.setText(cua.getChieurong());
            edCaocua.setText(cua.getChieucao());
            edHoechancanh.setText(cua.getHochancanh());
            edSobo.setText(cua.getSobo());
        }else if(c == 2){
            Cuadata cuadata = (Cuadata) intent.getSerializableExtra("cuadata");

            Intent intent1 = getIntent();
            id_ct = intent1.getStringExtra("id_ct");
            id_henhom = cuadata.getIdHenhom();
            id_cua = cuadata.getIdCuaData();
            byte[] decodedString = Base64.decode(cuadata.getAnhcuadata(), Base64.DEFAULT);
            Bitmap imgBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            anhcua = cuadata.getAnhcuadata();
            image_cua.setImageBitmap(imgBitMap);
            edTenCua.setText(cuadata.getTencuaData());
        }
    }

    @OnClick(R.id.btnQuaylai)
    public void clickQuaylai(){
        finish();
    }
    @OnClick(R.id.btnLuuCua)
    public void clickLuu(){
        String tencua = edTenCua.getText().toString();
        if(tencua.isEmpty()){
            edTenCua.setError("Vui lòng nhập tên cửa");
            edTenCua.requestFocus();
            return;
        }
        if(edCaocua.getText().toString().isEmpty()){
            edCaocua.setError("Vui lòng nhập chiều cao cửa");
            edCaocua.requestFocus();
            return;
        }
        if(edRongcua.getText().toString().isEmpty()){
            edRongcua.setError("Vui lòng nhập chiều rộng cửa");
            edRongcua.requestFocus();
            return;
        }
        if(edSobo.getText().toString().isEmpty()){
            edSobo.setError("Vui lòng nhập số bộ cửa");
            edSobo.requestFocus();
            return;
        }
        if(edHoechancanh.getText().toString().isEmpty()){
            edHoechancanh.setError("Vui lòng nhập sô liệu hở chân cánh cửa");
            edHoechancanh.requestFocus();
            return;
        }
        float chieucao = Float.parseFloat(edCaocua.getText().toString());
        float chieurong = Float.parseFloat(edRongcua.getText().toString());
        int sobo = Integer.parseInt(edSobo.getText().toString());
        float hochancanh = Float.parseFloat(edHoechancanh.getText().toString());
        detailCuaPresenter.themCua(utils.getUrl(),id_cua,tencua,chieucao,chieurong,anhcua,id_ct,Integer.parseInt(id_henhom),sobo,hochancanh);
        Log.e("hsn",id_henhom);
        Log.e("idcua",id_cua);
    }

    @Override
    public void themmoiThanhcong(String text) {
        utils.showToast(text);
        Intent intent = new Intent(getApplicationContext(), CuaActivity.class);
        intent.putExtra("id_ct",id_ct);
        intent.putExtra("tenct",utils.getTenCt(id_ct));
        startActivity(intent);
        finish();
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
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
