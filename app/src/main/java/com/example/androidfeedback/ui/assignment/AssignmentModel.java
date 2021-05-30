package com.example.androidfeedback.ui.assignment;

import com.google.gson.annotations.SerializedName;

public class AssignmentModel {
    int id;
    @SerializedName("moduleName")
    String moduleName;
    @SerializedName("className")
    String className;
    @SerializedName("trainerName")
    String trainerName;
    @SerializedName("registrationCode")
    String registrationCode;

    public AssignmentModel(int id, String moduleName, String className, String trainerName, String registrationCode)
    {
        this.id = id;
        this.moduleName = moduleName;
        this.className = className;
        this.trainerName = trainerName;
        this.registrationCode = registrationCode;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getModuleName(){
        return moduleName;
    }
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    }

    public String getClassName(){
        return className;
    }
    public void setClassName(String className){
        this.className = className;
    }

    public String getTrainerName(){
        return trainerName;
    }
    public void setTrainerID(String trainerID){
        this.trainerName = trainerName;
    }

    public String getRegistrationCode(){
        return registrationCode;
    }
    public void setRegistrationCode(String registrationCode){
        this.registrationCode = registrationCode;
    }


}
