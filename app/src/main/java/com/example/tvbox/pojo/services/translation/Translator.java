package com.example.tvbox.pojo.services.translation;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Translator {
    private static final String TAG = "Translator";
    private ArrayList<ResponseData> listOfMovies = new ArrayList<>();

    public void translate (String title, String language) {

        String baseUrl = "https://api.mymemory.translated.net/get?q=";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mymemory.translated.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiTranslatorInterface apiInterface = retrofit.create(ApiTranslatorInterface.class);
        Call<ResponseData> call = apiInterface
                .getTranslatedTitle(baseUrl + title + "&langpair=pl|" + language);
       call.enqueue(new Callback<ResponseData>() {
           @Override
           public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
               setListOfMovies(response.body());
               Log.d(TAG, "onResponse: "+response.body().getResponseData().getTranslatedText());
           }

           @Override
           public void onFailure(Call<ResponseData> call, Throwable t) {

           }
       });
    }

    public ArrayList<ResponseData> getListOfMovies() {
        return listOfMovies;
    }

    private void setListOfMovies(ResponseData item) {
        this.listOfMovies.add(item);
    }
}
