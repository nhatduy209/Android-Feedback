package com.example.androidfeedback.ui.module;

import com.google.gson.annotations.SerializedName;

public class ModuleViewModel {


    @SerializedName("moduleID")
    private int moduleId;

    @SerializedName("adminID")
    private String adminId;

    @SerializedName("moduleName")
    private String moduleName;

    @SerializedName("startDate")
    private String startDate;

    @SerializedName("endDate")
    private String endDate;
//    private String moduleName;
//    private String startDate;
//    private String endDate;

    @SerializedName("feedbackTitleID")
    private String fbTitle;

    @SerializedName("feedbackStartTime")
    private String fbStartDate;

    @SerializedName("feedbackEndTime")
    private String fbEndDate;


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

    public ModuleViewModel(int moduleId,
            String adminId,
            String moduleName,
            String startDate,
            String endDate,
            String fbTitle,
            String fbStartDate,
            String fbEndDate){
        this.moduleId = moduleId;
        this.adminId =adminId;
        this.moduleName=moduleName;
        this.startDate = startDate;
        this.endDate =endDate;
        this.fbTitle = fbTitle;
        this.fbStartDate = fbStartDate;
        this.fbEndDate = fbEndDate;
    }

    public int getModuleId() {
        return moduleId;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getFbTitle() {
        return fbTitle;
    }

    public String getFbStartDate() {
        return fbStartDate;
    }

    public String getFbEndDate() {
        return fbEndDate;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setFbTitle(String fbTitle) {
        this.fbTitle = fbTitle;
    }

    public void setFbStartDate(String fbStartDate) {
        this.fbStartDate = fbStartDate;
    }

    public void setFbEndDate(String fbEndDate) {
        this.fbEndDate = fbEndDate;
    }

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getModuleName();
    }
}
