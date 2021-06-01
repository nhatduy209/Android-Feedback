package com.example.androidfeedback.ui.assignment;

import com.google.gson.annotations.SerializedName;

public class AssignmentModel {
    int id;
    @SerializedName("moduleId")
    int moduleId;
    @SerializedName("moduleName")
    String moduleName;

    @SerializedName("classId")
    int classId;
    @SerializedName("className")
    String className;

    @SerializedName("trainerId")
    String trainerId;
    @SerializedName("trainerName")
    String trainerName;
    @SerializedName("registrationCode")
    String registrationCode;

    //Check delete
    @SerializedName("moduleIsDelete")
    boolean moduleIsDelete;
    @SerializedName("classIsDelete")
    boolean classIsDelete;

    public boolean moduleIsDelete() {
        return moduleIsDelete;
    }
    public boolean classIsDelete() {
        return classIsDelete;
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

    public AssignmentModel(int id, String moduleName, String className, String trainerName, String registrationCode)
    {
        this.id = id;
        this.moduleName = moduleName;
        this.className = className;
        this.trainerName = trainerName;
        this.registrationCode = registrationCode;
    }

    public AssignmentModel(int moduleID, int classID, String trainerID)
    {
        this.moduleId = moduleID;
        this.classId = classID;
        this.trainerId = trainerID;

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getModuleId(){
        return moduleId;
    }
    public void setModuleId(int moduleId){
        this.moduleId = moduleId;
    }
    public String getModuleName(){
        return moduleName;
    }
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    }


    public int getClassId(){
        return classId;
    }
    public void setClassId(int classId){
        this.classId = classId;
    }
    public String getClassName(){
        return className;
    }
    public void setClassName(String className){
        this.className = className;
    }

    public String getTrainerId(){
        return trainerId;
    }
    public void setTrainerId(String trainerId){
        this.trainerId = trainerId;
    }

    public String getTrainerName(){
        return trainerName;
    }
    public void setTrainerName(String trainerName){
        this.trainerName = trainerName;
    }



    public String getRegistrationCode(){
        return registrationCode;
    }
    public void setRegistrationCode(String registrationCode){
        this.registrationCode = registrationCode;
    }


}
