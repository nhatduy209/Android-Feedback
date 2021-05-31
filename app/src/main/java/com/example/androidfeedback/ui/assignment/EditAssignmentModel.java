package com.example.androidfeedback.ui.assignment;

import com.google.gson.annotations.SerializedName;

public class EditAssignmentModel {
    @SerializedName("moduleName")
    String moduleName;
    @SerializedName("className")
    String className;
    @SerializedName("trainerName")
    String trainerName;

    public EditAssignmentModel(String moduleName,String className, String trainerName)
    {
        this.className = className;
        this.moduleName = moduleName;
        this.trainerName = trainerName;

    }

    public String moduleName(){
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
    public void setTrainerName(String trainerName){
        this.trainerName = trainerName;
    }

}
