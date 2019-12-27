package com.dell.actapp.network.api;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Kinh;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIKinh {
    @GET("congtrinh.php")
    Call<List<Kinh>> getKinh();
}
