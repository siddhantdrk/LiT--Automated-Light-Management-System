package com.my.lit.models;

import com.google.gson.annotations.SerializedName;

public class AreaBulbsItem {

    @SerializedName("bulb_id")
    private String bulbId;

    @SerializedName("_id")
    private String id;

    public String getBulbId() {
        return bulbId;
    }

    public void setBulbId(String bulbId) {
        this.bulbId = bulbId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}