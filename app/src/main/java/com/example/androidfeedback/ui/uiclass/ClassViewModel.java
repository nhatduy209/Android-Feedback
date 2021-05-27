package com.example.androidfeedback.ui.uiclass;

import com.google.gson.annotations.SerializedName;

public class ClassViewModel {

    @SerializedName("classID")
    private String  classId;
    @SerializedName("className")
    private String className;

    @SerializedName("startDate")
    private String startDate;

    @SerializedName("endDate")
    private String endDate;

    @SerializedName("capacity")
    private String Capacity;

    @SerializedName("userId")
    public String userId ;

    public String getUserId() {
        return userId;
    }

    @SerializedName("message")
    private String message ;

    @SerializedName("success")
    private String success ;

    public String getMessage() {
        return message;
    }

    public String getSuccess() {
        return success;
    }

    @SerializedName("isDeleted")
    private boolean isDeleted ;

    public boolean isDeleted() {
        return isDeleted;
    }

    public ClassViewModel(
                          String className,
                          String startDate,
                          String endDate,
                          String Capacity,
                          boolean isDeleted
    ){
        this.className=className;
        this.startDate = startDate;
        this.endDate =endDate;
        this.Capacity = Capacity;
        this.isDeleted = isDeleted;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}
