package com.example.androidfeedback.ui.assignment;

public class AssignmentModel {
    int id;
    String moduleName;
    String className;
    String trainerID;
    String registrationCode;

    public AssignmentModel(int id, String moduleName, String className, String trainerID, String registrationCode)
    {
        this.id = id;
        this.moduleName = moduleName;
        this.className = className;
        this.trainerID = trainerID;
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

    public String getTrainerID(){
        return trainerID;
    }
    public void setTrainerID(String trainerID){
        this.trainerID = trainerID;
    }

    public String getRegistrationCode(){
        return registrationCode;
    }
    public void setRegistrationCode(String registrationCode){
        this.registrationCode = registrationCode;
    }


}
