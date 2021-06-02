package com.example.androidfeedback.ui.statistic;

import com.google.gson.annotations.SerializedName;

public class ClassStatisticViewModel {

    @SerializedName("classID")
    private int classID;
    @SerializedName("className")
    private String className;


    public ClassStatisticViewModel(int classID, String className) {
        this.classID = classID;
        this.className = className;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return className;
    }
}
