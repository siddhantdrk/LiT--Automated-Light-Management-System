package com.my.lit.models;

import com.google.gson.annotations.SerializedName;

public class UpdateLightStatusData {

    @SerializedName("newBulbStatus")
    private NewLightStatus newLightStatus;

    public NewLightStatus getNewLightStatus() {
        return newLightStatus;
    }

    public void setNewLightStatus(NewLightStatus newLightStatus) {
        this.newLightStatus = newLightStatus;
    }
}