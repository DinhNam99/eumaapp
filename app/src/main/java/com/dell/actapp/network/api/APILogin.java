package com.dell.actapp.network.api;

import com.dell.actapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APILogin {
    @GET("loginUser.php")
    Call<List<User>> getUser();
}
