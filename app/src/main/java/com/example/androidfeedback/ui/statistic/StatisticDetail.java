package com.example.androidfeedback.ui.statistic;

import java.util.ArrayList;

public class StatisticDetail {
    private String topicName;
    private ArrayList<StatisticQuestion> data;

    public StatisticDetail(String topicName, ArrayList<StatisticQuestion> data) {
        this.topicName = topicName;
        this.data = data;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ArrayList<StatisticQuestion> getData() {
        return data;
    }

    public void setData(ArrayList<StatisticQuestion> data) {
        this.data = data;
    }
}
