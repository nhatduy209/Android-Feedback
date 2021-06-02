package com.example.androidfeedback.ui.feedback;

import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopicFeedbackModel {
    @SerializedName("topicName")
    private String topicName;

    @SerializedName("questions")
    private ArrayList<QuestionViewModel> questions;

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

    public TopicFeedbackModel(String topicName, ArrayList<QuestionViewModel> questions){
        this.topicName = topicName;
        this.questions = questions;
    }

    public void setQuestions(ArrayList<QuestionViewModel> questions) {
        this.questions = questions;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ArrayList<QuestionViewModel> getQuestions() {
        return questions;
    }

    public String getTopicName() {
        return topicName;
    }
}
