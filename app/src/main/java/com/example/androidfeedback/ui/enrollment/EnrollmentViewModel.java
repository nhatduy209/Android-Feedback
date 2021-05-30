package com.example.androidfeedback.ui.enrollment;

import com.google.gson.annotations.SerializedName;

public class EnrollmentViewModel {

    @SerializedName("traineeId")
    private String TraineeID;

    @SerializedName("traineeUserName")
    private String TrainerName;

    @SerializedName("classId")
    private String ClassID;

    @SerializedName("className")
    private String ClassName;

    public EnrollmentViewModel(String TraineeID, String ClassName){
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

    public void setTrainerName(String trainerName) {
        TrainerName = trainerName;
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

    public String getTrainerName() {
        return TrainerName;
    }
}
