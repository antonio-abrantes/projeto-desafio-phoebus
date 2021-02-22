package com.aadev.aecomics.services;

import com.aadev.aecomics.helpers.ApiMarvelConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    public static Retrofit getRetrofitObject(){
        return new retrofit2.Retrofit.Builder()
                .baseUrl(ApiMarvelConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
