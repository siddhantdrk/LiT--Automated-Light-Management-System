package com.my.lit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaDataItem {

    @SerializedName("bulbs")
    private List<AreaBulbsItem> bulbs;

    @SerializedName("name")
    private String name;

    @SerializedName("_id")
    private String id;

    @SerializedName("floor")
    private int floor;

    @SerializedName("building")
    private String building;

    public List<AreaBulbsItem> getBulbs() {
        return bulbs;
    }

    public void setBulbs(List<AreaBulbsItem> bulbs) {
        this.bulbs = bulbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}