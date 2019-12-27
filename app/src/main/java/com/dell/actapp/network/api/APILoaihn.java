package com.dell.actapp.network.api;

import com.dell.actapp.model.Loaihn;
import com.dell.actapp.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APILoaihn {
    @FormUrlEncoded
    @POST("loaihn.php")
    Call<List<Loaihn>> getLoaihn(@Field("id_henhom") int id_henhom);
}
