package com.my.responses;

import com.google.gson.annotations.SerializedName;
import com.my.lit.models.LightDataItem;

import java.util.List;

public class GetLightsByAreaIdResponse {

    @SerializedName("data")
    private List<LightDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<LightDataItem> getData() {
        return data;
    }

    public void setData(List<LightDataItem> data) {
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