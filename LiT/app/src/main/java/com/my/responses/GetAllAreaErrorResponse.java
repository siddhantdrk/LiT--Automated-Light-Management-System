package com.my.responses;

import com.google.gson.annotations.SerializedName;

public class GetAllAreaErrorResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}