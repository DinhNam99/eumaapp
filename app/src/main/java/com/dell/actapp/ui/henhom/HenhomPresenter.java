package com.dell.actapp.ui.henhom;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Henhom;
import com.dell.actapp.network.api.APIHenhom;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HenhomPresenter {

    HenhomView henhomView;
    public HenhomPresenter(HenhomView henhomView){
        this.henhomView = henhomView;
    }

    public void display(String url){
        henhomView.showProgress();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APIHenhom apiHenhom = retrofit.create(APIHenhom.class);
        Call<List<Henhom>> call = apiHenhom.getHenhom();
        call.enqueue(new Callback<List<Henhom>>() {
            @Override
            public void onResponse(Call<List<Henhom>> call, Response<List<Henhom>> response) {
                List<Henhom> henhoms = response.body();
                henhomView.displayHenhom((ArrayList<Henhom>) henhoms);
                henhomView.hidProgress();
            }

            @Override
            public void onFailure(Call<List<Henhom>> call, Throwable t) {
                henhomView.displayFailer("Lỗi!");
                henhomView.hidProgress();
            }
        });
    }
}
