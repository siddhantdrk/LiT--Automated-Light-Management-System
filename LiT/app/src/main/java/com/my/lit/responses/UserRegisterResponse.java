package com.my.lit.responses;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("token")
    private String token;
    @SerializedName("user")
    private JsonObject user;

    public UserRegisterResponse(String error) {
        this.error = error;
    }

    public UserRegisterResponse(String token, JsonObject user) {
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
