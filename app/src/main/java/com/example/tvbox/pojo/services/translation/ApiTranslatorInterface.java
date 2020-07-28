package com.example.tvbox.pojo.services.translation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiTranslatorInterface {
    @GET()
    Call<ResponseData> getTranslatedTitle(@Url() String Url);
}
