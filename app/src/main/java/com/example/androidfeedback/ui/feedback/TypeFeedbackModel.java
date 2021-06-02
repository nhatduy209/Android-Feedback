package com.example.androidfeedback.ui.feedback;

import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TypeFeedbackModel {
    @SerializedName("typeID")
    private int typeID;

    @SerializedName("typeName")
    private String typeName;

    @SerializedName("isDelete")
    private boolean isDelete;

    @SerializedName("feedbacks")
    private ArrayList<FeedbackViewModel> feedbacks;

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

    public TypeFeedbackModel(int typeID,String typeName,boolean isDelete, ArrayList<FeedbackViewModel> feedbacks){
        this.typeID = typeID;
        this.typeName = typeName;
        this.isDelete = isDelete;
        this.feedbacks = feedbacks;
    }

    public ArrayList<FeedbackViewModel> getFeedbacks() {
        return feedbacks;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public void setFeedbacks(ArrayList<FeedbackViewModel> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getTypeName();
    }
}
