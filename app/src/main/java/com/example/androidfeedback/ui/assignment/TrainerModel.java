package com.example.androidfeedback.ui.assignment;

import com.google.gson.annotations.SerializedName;

public class TrainerModel {
    @SerializedName("trainerID")
    String trainerID;
    @SerializedName("trainerName")
    String trainerName;

    public TrainerModel(String trainerID, String trainerName)
    {
        this.trainerID = trainerID;
        this.trainerName = trainerName;
    }

    public String getTrainerId(){
        return trainerID;
    }
    public void setTrainerID(String trainerID){
        this.trainerID = trainerID;
    }
    public String getTrainerName(){
        return trainerName;
    }

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getTrainerName();
    }
}
