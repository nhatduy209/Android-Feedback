package com.example.androidfeedback.ui.statistic;

import com.google.gson.annotations.SerializedName;

public class ModuleStatisticViewModel {
    @SerializedName("moduleID")
    public int moduleID;
    @SerializedName("moduleName")
    public String moduleName;

    public ModuleStatisticViewModel(int moduleID, String moduleName) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int classID) {
        this.moduleID = classID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String className) {
        this.moduleName = className;
    }
    @Override
    public String toString() {
        return moduleName;
    }
}
