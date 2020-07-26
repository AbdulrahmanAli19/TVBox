package com.example.tvbox.ui.main;

import com.example.tvbox.pojo.TranslateModule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ApiInterface {
    @GET("get?q=")
    public Call<TranslateModule> getTranslatedTitle(@Query("langpair=en|it")String title);
}
