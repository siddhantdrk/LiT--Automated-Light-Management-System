package com.my.responses;

import com.google.gson.annotations.SerializedName;
import com.my.lit.models.AreaDataItem;

import java.util.List;

public class GetAllAreasResponse {

    @SerializedName("data")
    private List<AreaDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<AreaDataItem> getData() {
        return data;
    }

    public void setData(List<AreaDataItem> data) {
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