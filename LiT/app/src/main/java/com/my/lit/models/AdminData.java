package com.my.lit.models;

import com.google.gson.annotations.SerializedName;

public class AdminData {

    @SerializedName("admin")
    private Admin admin;

    @SerializedName("token")
    private String token;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}