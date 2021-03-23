package com.my.lit.models;

import java.util.HashMap;

public class Room {

    private  int room_no;
    private  String building;
    private  int floor;
    HashMap<String , Boolean> lightstate ;


    public Room(int room_no,String building , int floor) {
        this.room_no = room_no;
        this.building = building;
        this.floor = floor;

    }

    public int getRoom_no() {
        return room_no;
    }

    public String getBuilding() {
        return building;
    }

    public int getFloor() {
        return floor;
    }


    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public HashMap<String, Boolean> getLightstate() {
        return lightstate;
    }

    public void setLightstate(HashMap<String, Boolean> lightstate) {
        this.lightstate = lightstate;
    }
}
