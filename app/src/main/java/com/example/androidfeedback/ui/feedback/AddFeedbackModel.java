package com.example.androidfeedback.ui.feedback;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddFeedbackModel {

    @SerializedName("iD")
    private int feedbackId;

    @SerializedName("title")
    private String feedbackTitle;

    @SerializedName("adminID")
    private String adminId;

    @SerializedName("typeFeedbackID")
    private int typeFeedbackID;

    @SerializedName("lstQuestionID")
    private List<Integer> lstQuestionID;

    @SerializedName("message")
    private String message ;

    @SerializedName("success")
    private String success ;

    public String getMessage() {
        return message;
    }

    public String getSuccess() {
        return success;
    }

    public AddFeedbackModel(int feedbackId,
                             String feedbackTitle,
                             String adminId,
                            int typeFeedbackID,
                            List<Integer> lstQuestionID
    ){
        this.feedbackId = feedbackId;
        this.feedbackTitle=feedbackTitle;
        this.adminId = adminId;
        this.lstQuestionID = lstQuestionID;
        this.typeFeedbackID = typeFeedbackID;
    }

    public int getTypeFeedbackID() {
        return typeFeedbackID;
    }

    public List<Integer> getLstQuestionID() {
        return lstQuestionID;
    }

    public void setLstQuestionID(List<Integer> lstQuestionID) {
        this.lstQuestionID = lstQuestionID;
    }

    public void setTypeFeedbackID(int typeFeedbackID) {
        this.typeFeedbackID = typeFeedbackID;
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
