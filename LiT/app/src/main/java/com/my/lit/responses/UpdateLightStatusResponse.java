package com.my.lit.responses;

import com.google.gson.annotations.SerializedName;
import com.my.lit.models.UpdateLightStatusData;

public class UpdateLightStatusResponse {

    @SerializedName("data")
    private UpdateLightStatusData updateLightStatusData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public UpdateLightStatusData getUpdateLightStatusData() {
        return updateLightStatusData;
    }

    public void setUpdateLightStatusData(UpdateLightStatusData updateLightStatusData) {
        this.updateLightStatusData = updateLightStatusData;
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