package com.example.androidfeedback.ui.statistic;

import android.util.Pair;

import java.util.ArrayList;

public class PieBaseOnTopic {
    private Pair<String, ArrayList<PieChartViewModel>> data;

    public Pair<String, ArrayList<PieChartViewModel>> getData() {
        return data;
    }

    public void setData(Pair<String, ArrayList<PieChartViewModel>> data) {
        this.data = data;
    }
    public PieBaseOnTopic(){

    }
}
