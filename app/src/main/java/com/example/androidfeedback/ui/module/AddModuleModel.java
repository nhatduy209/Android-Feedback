package com.example.androidfeedback.ui.module;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AddModuleModel {

    @SerializedName("isDelete")
    private boolean isDelete;
    @SerializedName("adminID")
    private String adminId;

    @SerializedName("moduleName")
    private String moduleName;

    @SerializedName("startTime")
    private Date startTime;

    @SerializedName("endTime")
    private Date endTime;

    @SerializedName("feedbackTitleID")
    private int fbTitle;

    @SerializedName("feedbackStartTime")
    private Date fbStartTime;

    @SerializedName("feedbackEndTime")
    private Date fbEndTime;

    @SerializedName("message")
    private String message ;

    public String getMessage() {
        return message;
    }

    public String getSuccess() {
        return success;
    }

    @SerializedName("success")
    private String success ;

    public AddModuleModel(String adminId,String moduleName, Date startTime,Date endTime, boolean isDelete, Date fbStartTime, Date fbEndTime, int fbTitle){
        this.adminId = adminId;
        this.moduleName = moduleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fbStartTime = fbStartTime;
        this.fbEndTime = fbEndTime;
        this.fbTitle = fbTitle;
        this.isDelete = isDelete;
    }

    public Integer getFbTitle() {
        return fbTitle;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getFbEndTime() {
        return fbEndTime;
    }

    public Date getFbStartTime() {
        return fbStartTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setFbTitle(Integer fbTitle) {
        this.fbTitle = fbTitle;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setFbEndTime(Date fbEndTime) {
        this.fbEndTime = fbEndTime;
    }

    public void setFbStartTime(Date fbStartTime) {
        this.fbStartTime = fbStartTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
