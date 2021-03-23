package com.my.lit.responses;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

public class AdminLoginResponse implements Serializable {
    @SerializedName("token")
    private String token;
    @SerializedName("user")
    private JSONObject user;

    public AdminLoginResponse(String token, JSONObject user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getUser() {
        return user;
    }

    public void setUser(JSONObject user) {
        this.user = user;
    }
}
