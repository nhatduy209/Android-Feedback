package com.example.androidfeedback.ui.statistic;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StatisticViewModel {
    @SerializedName("classes")
    private ArrayList<ClassStatisticViewModel> classes;
    @SerializedName("courses")
    private ArrayList<ModuleStatisticViewModel> courses;

    public StatisticViewModel(ArrayList<ClassStatisticViewModel> classes, ArrayList<ModuleStatisticViewModel> courses) {
        this.classes = classes;
        this.courses = courses;
    }

    public StatisticViewModel(){

    }
    public ArrayList<ClassStatisticViewModel> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassStatisticViewModel> classes) {
        this.classes = classes;
    }

    public ArrayList<ModuleStatisticViewModel> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<ModuleStatisticViewModel> courses) {
        this.courses = courses;
    }
}
