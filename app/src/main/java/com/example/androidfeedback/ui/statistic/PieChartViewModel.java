package com.example.androidfeedback.ui.statistic;

import com.google.gson.annotations.SerializedName;

public class PieChartViewModel {
    @SerializedName("value")
    private int value;
    @SerializedName("percent")
    private float percent;

    public PieChartViewModel(int value, float percent) {
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
}
