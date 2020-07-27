package com.example.tvbox.pojo.services.translation;

import android.util.Log;

import com.example.tvbox.pojo.services.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Translator {

    private static final String TAG = Translator.class.getSimpleName();

    public void translate(String title){
        System.out.println("hii");

        String baseUrl = "https://api.mymemory.translated.net/get?q=";

        String language = "&langpair=pl|ar";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mymemory.translated.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<ResponseData> call ;

        call = apiInterface.getTranslatedTitle(baseUrl+title+language);

        call.enqueue(new Callback<ResponseData>() {

            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d(TAG, "onResponse: "+response.body().getResponseData()
                        .getTranslatedText());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
