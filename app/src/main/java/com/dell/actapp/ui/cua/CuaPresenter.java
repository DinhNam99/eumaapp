package com.dell.actapp.ui.cua;

import android.util.Log;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Cua;
import com.dell.actapp.model.Cuadata;
import com.dell.actapp.network.api.APICongtrinh;
import com.dell.actapp.network.api.APICua;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CuaPresenter {

    CuaView cuaView;
    public CuaPresenter(CuaView cuaView){
        this.cuaView = cuaView;
    }

    public void display(String url,String id_ct){
        cuaView.showProgress();
        ArrayList<Cua> cuaArrayList = new ArrayList<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APICua apiCua = retrofit.create(APICua.class);
        Call<List<Cua>> call = apiCua.getCua();
        call.enqueue(new Callback<List<Cua>>() {
            @Override
            public void onResponse(Call<List<Cua>> call, Response<List<Cua>> response) {
                List<Cua> cuaRespone = response.body();
                for(int i = 0; i<cuaRespone.size(); i++){
                    if(cuaRespone.get(i).getIdCt().equals(id_ct)){
                        cuaArrayList.add(cuaRespone.get(i));
                    }
                }
                cuaView.displayCuabyCt(cuaArrayList);
                cuaView.hidProgress();
            }

            @Override
            public void onFailure(Call<List<Cua>> call, Throwable t) {
                cuaView.displayFailer("Vui lòng kiểm tra kết nối!");
                cuaView.hidProgress();
            }
        });
    }

    public void displayDataCua(String url, int id_henhom, String loaihenhom, String loaicua){
        cuaView.showProgress();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APICua apiCua = retrofit.create(APICua.class);
        Call<List<Cuadata>> call = apiCua.getDataCua(id_henhom,loaihenhom,loaicua);
        call.enqueue(new Callback<List<Cuadata>>() {
            @Override
            public void onResponse(Call<List<Cuadata>> call, Response<List<Cuadata>> response) {
                Log.e("size",response.body().size()+"");
                ArrayList<Cuadata> cuaArrayList = (ArrayList<Cuadata>) response.body();
                cuaView.displayCuadata(cuaArrayList);
                cuaView.hidProgress();
            }

            @Override
            public void onFailure(Call<List<Cuadata>> call, Throwable t) {
                cuaView.displayFailer("Vui lòng kiểm tra kết nối!");
                cuaView.hidProgress();
            }
        });
    }
}
