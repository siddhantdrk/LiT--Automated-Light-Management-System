package com.my.lit.responses;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class AdminLoginResponse {

    @SerializedName("error")
    private String error;
    @SerializedName("token")
    private String token;
    @SerializedName("user")
    private JsonObject admin;

    public AdminLoginResponse(String error) {
        this.error = error;
    }

    public AdminLoginResponse(String token, JsonObject admin) {
        this.token = token;
        this.admin = admin;
    }

    public String getError() {
        return error;
    }

    public String getToken() {
        return token;
    }

    public JsonObject getAdmin() {
        return admin;
    }
}
