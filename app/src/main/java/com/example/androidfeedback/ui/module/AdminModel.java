package com.example.androidfeedback.ui.module;

import com.google.gson.annotations.SerializedName;

public class AdminModel {
    @SerializedName("adminID")
    String adminID;
    @SerializedName("adminUsername")
    String adminUserName;

    public AdminModel(String adminID, String adminUsername)
    {
        this.adminID = adminID;
        this.adminUserName = adminUserName;
    }

    public String getAdminID() {
        return adminID;
    }

    public String getAdminName() {
        return adminUserName;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public void setAdminName(String adminName) {
        this.adminUserName = adminName;
    }

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getAdminName();
    }
}
