package com.dell.actapp.network.api;

import com.dell.actapp.model.Loaicua;
import com.dell.actapp.model.Loaihn;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APILoaicua {
    @FormUrlEncoded
    @POST("loaicua.php")
    Call<List<Loaicua>> getLoaiCua(@Field("id_henhom") int id_henhom, @Field("loaihenhom") String loaihenhom);
}
