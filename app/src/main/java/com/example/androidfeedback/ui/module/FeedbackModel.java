package com.example.androidfeedback.ui.module;

import com.google.gson.annotations.SerializedName;

public class FeedbackModel {
    @SerializedName("feedbackID")
    int feedbackID;
    @SerializedName("lstFeedbackTitle")
    String lstFeedbackTitle;

    public FeedbackModel(int feedbackID, String lstFeedbackTitle)
    {
        this.feedbackID = feedbackID;
        this.lstFeedbackTitle = lstFeedbackTitle;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public String getLstFeedbackTitle() {
        return lstFeedbackTitle;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public void setLstFeedbackTitle(String lstFeedbackTitle) {
        this.lstFeedbackTitle = lstFeedbackTitle;
    }
    @Override
    public String toString()  {
        return this.lstFeedbackTitle;//s nãy kê trả id
    }
}
