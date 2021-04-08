package com.my.lit.api;

import com.my.lit.models.UpdateLightStatus;
import com.my.lit.responses.AdminAuthResponse;
import com.my.lit.responses.GetAllAreasResponse;
import com.my.lit.responses.GetLightsByAreaIdResponse;
import com.my.lit.responses.GuestAuthResponse;
import com.my.lit.responses.RequestLightResponse;
import com.my.lit.responses.ResetLightResponse;
import com.my.lit.responses.UpdateLightStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface
ApiServices {

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

    @GET("api/v1/admin/bulbs/area/{AREA_ID}/")
    Call<GetLightsByAreaIdResponse> getLightsByAreaIdAdmin(
            @Path("AREA_ID") String areaId,
            @Header("Authorization") String token
    );

    @GET("api/v1/user/bulbs/area/{AREA_ID}/")
    Call<GetLightsByAreaIdResponse> getLightsByAreaIdGuest(
            @Path("AREA_ID") String areaId,
            @Header("Authorization") String token
    );

    @POST("api/v1/admin/set/bulb/")
    Call<UpdateLightStatusResponse> updateLightStatus(
            @Body UpdateLightStatus update,
            @Header("Authorization") String token
    );

    @POST("api/v1/user/request/bulb")
    Call<RequestLightResponse> requestLight(
            @Body UpdateLightStatus update,
            @Header("Authorization") String token
    );

    @POST("api/v1/admin/reset/bulb")
    Call<ResetLightResponse> resetLights(
            @Header("Authorization") String token
    );
}
