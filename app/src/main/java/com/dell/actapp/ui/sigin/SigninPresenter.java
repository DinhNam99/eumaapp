package com.dell.actapp.ui.sigin;

import com.dell.actapp.model.Message;
import com.dell.actapp.network.api.APISigin;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SigninPresenter implements SigninPresenterInterface {

    SigninView signinView;
    public SigninPresenter(SigninView signinView){
        this.signinView = signinView;
    }
    @Override
    public void signin(String url, String tk, String mk,String thumnail) {
        signinView.showLoadingDialog();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APISigin apiSigin = retrofit.create(APISigin.class);
        Call<List<Message>> call = apiSigin.signinUser(tk,mk,thumnail);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messageList = response.body();
                for (int i = 0; i < messageList.size(); i++) {
                    if (messageList.get(i).getMessage().equals("success")) {
                        signinView.hideLoadingDialog();
                        signinView.showSiginSuccess("Đăng kí thành công!");
                    } else {
                        signinView.hideLoadingDialog();
                        signinView.showErrorSignin("Đăng kí thất bại!");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                signinView.hideLoadingDialog();
                signinView.showErrorSignin("Đăng kí thất bại!");
            }
        });
    }
}
