package com.example.androidfeedback.ui.statistic;

public class StatisticQuestion {
    private String questionID;
    private int value;
    private float percent;

    public StatisticQuestion(String questionID, int value, float percent) {
        this.questionID = questionID;
        this.value = value;
        this.percent = percent;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
}
