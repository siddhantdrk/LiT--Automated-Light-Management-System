package com.my.lit.responses;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {
    @SerializedName("token")
    private String token;
    @SerializedName("user")
    private JsonObject user;

    public UserLoginResponse(String token, JsonObject user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JsonObject getUser() {
        return user;
    }

    public void setUser(JsonObject user) {
        this.user = user;
    }
}
