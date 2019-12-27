package com.dell.actapp.ui.login;


import android.util.Log;

import com.dell.actapp.model.User;
import com.dell.actapp.network.api.APILogin;
import com.dell.actapp.network.source.AuthData;
import com.dell.actapp.utils.App;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginPresenter implements LoginPresenterInerface {

    LoginView loginView;
    public  LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }
    @Override
    public void login(final String url, final String username, String password) {
        loginView.showLoadingDialog();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        APILogin apiLogin = retrofit.create(APILogin.class);
        Call<List<User>> call = apiLogin.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                for(int i = 0; i<users.size(); i++){
                    if(username.equals(users.get(i).getUsername())&&password.equals(users.get(i).getPassword())){
                        loginView.hideLoadingDialog();
                        loginView.showLoginSuccess("Đăng nhập thành công!");
                        AuthData.getInstance().saveUser(users.get(i));
                    }else{
                        loginView.hideLoadingDialog();
                        loginView.showErrorLogin("Đăng nhập thất bại!");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                loginView.hideLoadingDialog();
                loginView.showErrorLogin("Đăng nhập thất bại!");
            }
        });
    }
}
