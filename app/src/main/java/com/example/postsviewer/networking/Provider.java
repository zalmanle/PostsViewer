package com.example.postsviewer.networking;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Provider {

    private static Retrofit instance;
    private static final String BASE_URL = "https://assets-production.applicaster.com/";


    public static Retrofit getRetrofitInstance(){

        if(instance == null) {

            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return instance;
    }

}
