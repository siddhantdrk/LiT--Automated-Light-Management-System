package com.my.lit.responses;

import com.google.gson.annotations.SerializedName;

public class RequestLightResponse {

    @SerializedName("data")
    private String data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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