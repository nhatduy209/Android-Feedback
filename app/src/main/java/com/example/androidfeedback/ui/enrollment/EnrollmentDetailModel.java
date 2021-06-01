package com.example.androidfeedback.ui.enrollment;

import com.google.gson.annotations.SerializedName;

public class EnrollmentDetailModel {
    @SerializedName("startTime")
    private String startDate;

    @SerializedName("endTime")
    private String endDate;
    @SerializedName("capacity")
    private String Capacity;

    @SerializedName("phone")
    private String phone;

    @SerializedName("traineeName")
    private String traineeName ;

    @SerializedName("address")
    private String address ;

    @SerializedName("email")
    private String email ;


    @SerializedName("traineeID")
    private String TraineeID;

    @SerializedName("cLassID")
    private String ClassID;

    @SerializedName("className")
    private String ClassName;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getPhone() {
        return phone;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }


    public EnrollmentDetailModel(String TraineeID, String ClassName){
        this.TraineeID = TraineeID;
        this.ClassName = ClassName;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setTraineeID(String traineeID) {
        TraineeID = traineeID;
    }


    public String getClassID() {
        return ClassID;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getTraineeID() {
        return TraineeID;
    }

}
