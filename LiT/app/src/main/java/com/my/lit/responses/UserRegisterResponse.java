package com.my.lit.responses;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRegisterResponse implements Serializable {

    @SerializedName("token")
    private final String token;
    @SerializedName("user")
    private final JsonObject user;

    public UserRegisterResponse(String token, JsonObject user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public JsonObject getUser() {
        return user;
    }
}
