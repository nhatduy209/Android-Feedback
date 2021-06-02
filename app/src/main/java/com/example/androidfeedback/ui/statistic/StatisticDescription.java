package com.example.androidfeedback.ui.statistic;

import android.util.Pair;

import java.util.ArrayList;

public class StatisticDescription {
    private  ArrayList<Pair<Integer,String>> description = new ArrayList<>();
    public  StatisticDescription(){
        description.add(new Pair<>(1,"Strongly Agree"));
        description.add(new Pair<>(2,"Agree"));
        description.add(new Pair<>(3,"Neural"));
        description.add(new Pair<>(4,"Disagree"));
        description.add(new Pair<>(5,"Strongly Disagree"));
    }
    public  ArrayList<Pair<Integer,String>> getDescription(){
        return description;
    }
    public  String getString(int value){
        return description.get(value-1).second;
    }
}
