package com.dell.actapp.network.api;

import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Cua;
import com.dell.actapp.model.Cuadata;
import com.dell.actapp.model.Loaicua;
import com.dell.actapp.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APICua {
    @GET("cua.php")
    Call<List<Cua>> getCua();

    @FormUrlEncoded
    @POST("datacua.php")
    Call<List<Cuadata>> getDataCua(@Field("id_henhom") int id_henhom, @Field("loaihenhom") String loaihenhom, @Field("loaicua") String loaicua);

    @FormUrlEncoded
    @POST("insertCua.php")
    Call<List<Message>> themmoiCua(@Field("id_cua") String id_cua,
                                   @Field("tencua") String tencua,
                                   @Field("chieucao") float chieucao,
                                   @Field("chieurong") float chieurong,
                                   @Field("anhcua") String anhcua,
                                   @Field("id_ct") String id_ct,
                                   @Field("id_henhom") int id_henhom,
                                   @Field("sobo") int sobo,
                                  @Field("hochancanh") float hochancanh);
}
