package com.example.tvbox.pojo.data;

import com.example.tvbox.pojo.services.translation.ApiTranslatorInterface;
import com.example.tvbox.pojo.services.translation.ResponseData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChannelsClient {
    private static final String BASE_URL = "https://api.mymemory.translated.net/" ;
    private ApiTranslatorInterface apiInterface ;
    private static ChannelsClient INSTANCES ;
    private String Url ;
    public ChannelsClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         apiInterface = retrofit.create(ApiTranslatorInterface.class);
    }

    public static ChannelsClient getINSTANCES() {
        if ( null == INSTANCES){
            INSTANCES = new ChannelsClient();
        }
        return INSTANCES;
    }

    public Call<ResponseData> scrapeData (){
        return apiInterface.getTranslatedTitle(Url);
    }

    public void setUrl(String url) {
        Url = url;
    }
}
