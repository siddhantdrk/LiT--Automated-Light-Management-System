package com.my.lit.api;

import com.my.lit.responses.AdminLoginResponse;
import com.my.lit.responses.UserLoginResponse;
import com.my.lit.responses.UserRegisterResponse;
import com.my.lit.responses.loginRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserServices {

    @FormUrlEncoded
    @POST("user/signup")
     Call<UserRegisterResponse>userRegister(
            @Field("email") String email,
            @Field("password") String password,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName
    );

    @FormUrlEncoded
    @POST("user/signin")
    Call<UserLoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<loginRegisterResponse> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("login")
    Call<loginRegisterResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("admin/signin")
    Call<AdminLoginResponse> adminLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
