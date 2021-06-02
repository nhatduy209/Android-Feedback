package com.example.androidfeedback.ui.question;

import com.google.gson.annotations.SerializedName;

public class QuestionTopicModel {

    @SerializedName("questionID")
    private int questionID;

    @SerializedName("questionContent")
    private String questionContent;

    @SerializedName("topicID")
    private int topicID;
//
//    @SerializedName("message")
//    private String message ;
//
//    @SerializedName("success")
//    private String success ;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public String getSuccess() {
//        return success;
//    }

    public QuestionTopicModel(int questionID, String questionContent, int topicID ){
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.topicID = topicID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getTopicID() {
        return topicID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setQuestionContent(String questionName) {
        this.questionContent = questionName;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }
}
