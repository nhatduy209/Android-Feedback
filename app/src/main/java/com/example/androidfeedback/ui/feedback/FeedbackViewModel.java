package com.example.androidfeedback.ui.feedback;

import com.google.gson.annotations.SerializedName;

public class FeedbackViewModel {

    @SerializedName("feedbackID")
    private int feedbackId;

    @SerializedName("title")
    private String feedbackTitle;

    @SerializedName("adminID")
    private String adminId;

    public FeedbackViewModel( int feedbackId,
                           String feedbackTitle,
                           String adminId
    ){
        this.feedbackId = feedbackId;
        this.feedbackTitle=feedbackTitle;
        this.adminId = adminId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public String getAdminId() {
        return adminId;
    }


    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
