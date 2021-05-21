package com.example.androidfeedback.ui.feedback;

public class FeedbackViewModel {
    private String feedbackId;
    private String feedbackTitle;
    private String adminId;
    public FeedbackViewModel( String feedbackId,
                           String feedbackTitle,
                           String adminId
    ){
        this.feedbackId = feedbackId;
        this.feedbackTitle=feedbackTitle;
        this.adminId = adminId;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public String getAdminId() {
        return adminId;
    }


    public void setFeedbackId(String classId) {
        this.feedbackId = feedbackId;
    }

    public void setFeedbackTitle(String className) {
        this.feedbackTitle = feedbackTitle;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
