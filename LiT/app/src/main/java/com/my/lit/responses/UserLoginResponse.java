package com.my.lit.responses;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("token")
    private String token;
    @SerializedName("user")
    private JsonObject user;

    public UserLoginResponse(String error) {
        this.error = error;
    }

    public UserLoginResponse(String token, JsonObject user) {
        this.token = token;
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public String getToken() {
        return token;
    }

    public JsonObject getUser() {
        return user;
    }
}
