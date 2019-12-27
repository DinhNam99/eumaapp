package com.dell.actapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.network.api.APICongtrinh;
import com.dell.actapp.network.source.AuthData;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {
    public String url = "http://namnam.atwebpages.com/";
    private Context context;
    public Utils(Context context){
        this.context = context;
    }
    public void showToast(String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public String getUrl() {
        return url;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getPhoto(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public String getTenCt(String id_ct){
        String tenct = "";
        ArrayList<CongTrinh> congTrinhs = new ArrayList<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APICongtrinh apiCongtrinh = retrofit.create(APICongtrinh.class);
        Call<List<CongTrinh>> call = apiCongtrinh.getCongTrinh();
        call.enqueue(new Callback<List<CongTrinh>>() {
            @Override
            public void onResponse(Call<List<CongTrinh>> call, Response<List<CongTrinh>> response) {
                List<CongTrinh> congTrinhResponse = response.body();
                for(int i = 0; i<congTrinhResponse.size(); i++) {
                    congTrinhs.add(congTrinhResponse.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<CongTrinh>> call, Throwable t) {

            }
        });
        for(int i = 0; i< congTrinhs.size(); i++){
            if(congTrinhs.get(i).getIdCt().equals(id_ct)){
                tenct = congTrinhs.get(i).getTenct();
            }
        }
        return tenct;

    }
}
