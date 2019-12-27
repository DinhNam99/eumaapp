package com.dell.actapp.network.api;

import com.dell.actapp.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APISigin {
    @FormUrlEncoded
    @POST("siginUsers.php")
    Call<List<Message>> signinUser( @Field("username") String product_name,
                                    @Field("password") String desrciption,
                                    @Field("thumnail") String thumnail);
}
