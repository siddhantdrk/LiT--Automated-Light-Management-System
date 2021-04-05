package com.my.lit.responses;

import com.google.gson.annotations.SerializedName;
import com.my.lit.models.AdminData;

public class AdminAuthResponse {

    @SerializedName("data")
    private AdminData adminData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public AdminData getAdminData() {
        return adminData;
    }

    public void setAdminData(AdminData adminData) {
        this.adminData = adminData;
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