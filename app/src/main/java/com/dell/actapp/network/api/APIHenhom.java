package com.dell.actapp.network.api;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Henhom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIHenhom {
    @GET("henhom.php")
    Call<List<Henhom>> getHenhom();

}
