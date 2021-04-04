package com.my.lit.models;

import com.google.gson.annotations.SerializedName;

public class LightDataItem {

    @SerializedName("area")
    private String area;

    @SerializedName("name")
    private String name;

    @SerializedName("impressionsON")
    private int impressionsON;

    @SerializedName("_id")
    private String id;

    @SerializedName("impressionsOFF")
    private int impressionsOFF;

    @SerializedName("status")
    private boolean status;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImpressionsON() {
        return impressionsON;
    }

    public void setImpressionsON(int impressionsON) {
        this.impressionsON = impressionsON;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImpressionsOFF() {
        return impressionsOFF;
    }

    public void setImpressionsOFF(int impressionsOFF) {
        this.impressionsOFF = impressionsOFF;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}