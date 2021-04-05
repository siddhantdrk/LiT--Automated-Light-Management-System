package com.my.lit.models;

import com.google.gson.annotations.SerializedName;

public class UpdateLightStatus {

    @SerializedName("bulbId")
    private String bulbId;

    @SerializedName("status")
    private boolean status;

    public UpdateLightStatus(String bulbId, boolean status) {
        this.bulbId = bulbId;
        this.status = status;
    }

    public String getBulbId() {
        return bulbId;
    }

    public void setBulbId(String bulbId) {
        this.bulbId = bulbId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}