package com.dell.actapp.fragment.congtrinh;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Message;
import com.dell.actapp.network.api.APICongtrinh;
import com.dell.actapp.network.source.AuthData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CongtrinhPresenter {

    CongtrinhView congtrinhView;
    public CongtrinhPresenter(CongtrinhView congtrinhView){
        this.congtrinhView = congtrinhView;
    }

    public void displayCT(String url){
        ArrayList<CongTrinh> congTrinhArrayList = new ArrayList<>();
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
                String id_user = AuthData.getInstance().getUser().getId();
                for(int i = 0; i<congTrinhResponse.size(); i++){
                    if(congTrinhResponse.get(i).getIdUser().equals(id_user)){
                        congTrinhArrayList.add(congTrinhResponse.get(i));
                    }
                }
                congtrinhView.displayCongTrinh(congTrinhArrayList);
            }

            @Override
            public void onFailure(Call<List<CongTrinh>> call, Throwable t) {
                congtrinhView.displayFailer("Vui lòng kiểm tra kết nối!");
            }
        });
    }

    public void themCt(String url, String tenct, String id_user){
        congtrinhView.showProgress();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APICongtrinh apiCongtrinh = retrofit.create(APICongtrinh.class);
        Call<List<Message>> call = apiCongtrinh.themmoiCt(tenct,id_user);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messageList = response.body();
                for (int i = 0; i < messageList.size(); i++) {
                    if (messageList.get(i).getMessage().equals("success")) {
                        congtrinhView.hidProgress();
                        congtrinhView.themmoiThanhcong("Thêm mới thành công!",tenct,messageList.get(i).getId()+"");
                    } else {
                        congtrinhView.hidProgress();
                        congtrinhView.themmoiThatbai("Thêm mới thất bại!");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                congtrinhView.hidProgress();
                congtrinhView.themmoiThatbai("Thêm mới thất bại");
            }
        });
    }
}
