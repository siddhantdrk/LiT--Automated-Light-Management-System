package com.my.lit.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthErrorResponse implements Serializable {
    @SerializedName("error")
    private String error;

    public AuthErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
