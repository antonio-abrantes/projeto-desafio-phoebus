package com.aadev.aecomics.services;

import com.aadev.aecomics.models.ResponseComics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {

    @GET("comics")
    Call<ResponseComics> getComics(
            @Query("ts") String ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash
    );
}
