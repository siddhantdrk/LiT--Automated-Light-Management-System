package com.my.lit.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.43.98:2000/api/v1/";
    private static final String BASE_URL_TEMP = "http://192.168.43.199:3000/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL_TEMP)
                .addConverterFactory(GsonConverterFactory
                        .create()).build();

    }

    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public UserServices getUserServices() {
        return retrofit.create(UserServices.class);
    }
}
