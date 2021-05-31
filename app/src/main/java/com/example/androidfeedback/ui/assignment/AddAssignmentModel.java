package com.example.androidfeedback.ui.assignment;

import com.google.gson.annotations.SerializedName;

public class AddAssignmentModel {
    @SerializedName("moduleID")
    int moduleId;
    @SerializedName("classID")
    int classId;
    @SerializedName("trainerID")
    String trainerId;

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

    public AddAssignmentModel(int classID,int moduleID, String trainerID)
    {
        this.moduleId = moduleID;
        this.classId = classID;
        this.trainerId = trainerID;

    }

    public int getModuleId(){
        return moduleId;
    }
    public void setModuleId(int moduleId){
        this.moduleId = moduleId;
    }

    public int getClassId(){
        return classId;
    }
    public void setClassId(int classId){
        this.classId = classId;
    }

    public String getTrainerId(){
        return trainerId;
    }
    public void setTrainerId(String trainerId){
        this.trainerId = trainerId;
    }

}
