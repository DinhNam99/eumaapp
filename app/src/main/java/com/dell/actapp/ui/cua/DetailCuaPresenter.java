package com.dell.actapp.ui.cua;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Message;
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

public class DetailCuaPresenter {
    public DetailCuaView detailCuaView;
    public DetailCuaPresenter(DetailCuaView detailCuaView){
        this.detailCuaView = detailCuaView;
    }

    public void themCua(String url,String id_cua,String tencua,float chieucao, float chieurong, String anhcua, String id_ct, int id_henhom,int sobo, float hochancanh){
        detailCuaView.showProgress();
        ArrayList<CongTrinh> congTrinhArrayList = new ArrayList<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APICua apiCua = retrofit.create(APICua.class);
        Call<List<Message>> call = apiCua.themmoiCua(id_cua,tencua,chieucao,chieurong,anhcua,id_ct,id_henhom,sobo,hochancanh);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messageList = response.body();
                for (int i = 0; i < messageList.size(); i++) {
                    if (messageList.get(i).getMessage().equals("success")) {
                        detailCuaView.hidProgress();
                        detailCuaView.themmoiThanhcong("Lưu thành công!");
                    } else {
                        detailCuaView.hidProgress();
                        detailCuaView.themmoiThatbai("Lưu thất bại!");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                detailCuaView.hidProgress();
                detailCuaView.themmoiThatbai("Lưu thất bại");
            }
        });
    }
}
