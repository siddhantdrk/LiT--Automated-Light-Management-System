package com.my.lit.responses;

import com.google.gson.annotations.SerializedName;
import com.my.lit.models.GuestData;

public class GuestAuthResponse {

    @SerializedName("data")
    private GuestData guestData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public GuestData getGuestData() {
        return guestData;
    }

    public void setGuestData(GuestData guestData) {
        this.guestData = guestData;
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