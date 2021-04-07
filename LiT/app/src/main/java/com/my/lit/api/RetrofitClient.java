package com.my.lit.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://appbots-ailights.herokuapp.com/";
    private static final String BASE_URL_TEMP = "http://192.168.43.199:3000/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    private RetrofitClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //TODO: Change level (!BODY)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory
                        .create()).client(okHttpClient).build();

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
