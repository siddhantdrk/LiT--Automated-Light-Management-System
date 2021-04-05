package com.my.lit.responses;

import com.google.gson.annotations.SerializedName;
import com.my.lit.models.AuthErrorData;

public class AuthErrorResponse {

    @SerializedName("authErrorData")
    private AuthErrorData authErrorData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public AuthErrorData getAuthErrorData() {
        return authErrorData;
    }

    public void setAuthErrorData(AuthErrorData authErrorData) {
        this.authErrorData = authErrorData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}