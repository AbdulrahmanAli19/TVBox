package com.example.tvbox.pojo.services.translation;

import com.example.tvbox.pojo.services.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


interface ApiInterface {
    @GET()
    Call<ResponseData> getTranslatedTitle(@Url() String Url);

}
