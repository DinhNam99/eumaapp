package com.dell.actapp.network.api;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Message;
import com.dell.actapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APICongtrinh {

    @GET("congtrinh.php")
    Call<List<CongTrinh>> getCongTrinh();

    @FormUrlEncoded
    @POST("insertCt.php")
    Call<List<Message>> themmoiCt(@Field("tenct") String tenct,
                                   @Field("id_user") String id_user);
    @FormUrlEncoded
    @POST("deleteCt.php")
    Call<List<Message>> deleteProduct(
            @Field("id_ct") String id);
}
