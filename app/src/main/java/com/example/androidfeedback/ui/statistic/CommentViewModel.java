package com.example.androidfeedback.ui.statistic;

import com.google.gson.annotations.SerializedName;

public class CommentViewModel {
    @SerializedName("traineeID")
    private String traineeID;
    @SerializedName("content")
    private String content;


    public CommentViewModel(String traineeID, String content) {
        this.traineeID = traineeID;
        this.content = content;
    }

    public String getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(String traineeID) {
        this.traineeID = traineeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
