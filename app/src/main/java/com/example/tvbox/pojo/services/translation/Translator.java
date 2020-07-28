package com.example.tvbox.pojo.services.translation;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Translator {
    private static final String TAG = "Translator";
    private final MutableLiveData<ResponseData> listOfMovies = new MutableLiveData<ResponseData>();

    public MutableLiveData<ResponseData> translate (String title, String language) {

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
                listOfMovies.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                listOfMovies.postValue(null);
            }
        });
        return listOfMovies;
    }
}
