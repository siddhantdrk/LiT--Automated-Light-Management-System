package com.my.lit.api;

import com.my.responses.AdminAuthResponse;
import com.my.responses.GetAllAreasResponse;
import com.my.responses.GuestAuthResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserServices {

    @FormUrlEncoded
    @POST("api/v1/user/signup")
    Call<GuestAuthResponse> userRegister(
            @Field("email") String email,
            @Field("password") String password,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName
    );

    @FormUrlEncoded
    @POST("api/v1/user/signin")
    Call<GuestAuthResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("api/v1/admin/signin")
    Call<AdminAuthResponse> adminLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/v1/user/area")
    Call<GetAllAreasResponse> getAllAreasGuest(
            @Header("Authorization") String token
    );

    @GET("api/v1/admin/area")
    Call<GetAllAreasResponse> getAllAreasAdmin(
            @Header("Authorization") String token
    );
}
