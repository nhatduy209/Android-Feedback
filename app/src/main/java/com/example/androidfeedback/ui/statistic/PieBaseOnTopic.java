package com.example.androidfeedback.ui.statistic;

import java.util.ArrayList;

public class PieBaseOnTopic {
    private String topicName;
    private ArrayList<PieChartViewModel> value;

    public PieBaseOnTopic(String topicName, ArrayList<PieChartViewModel> value) {
        this.topicName = topicName;
        this.value = value;
    }

    public ArrayList<PieChartViewModel> getValue() {
        return value;
    }

    public void setValue(ArrayList<PieChartViewModel> value) {
        this.value = value;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
