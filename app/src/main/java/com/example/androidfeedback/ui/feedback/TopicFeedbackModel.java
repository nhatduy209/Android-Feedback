package com.example.androidfeedback.ui.feedback;

import com.example.androidfeedback.ui.question.QuestionTopicModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopicFeedbackModel {
    @SerializedName("topicName")
    private String topicName;

    @SerializedName("questions")
    private ArrayList<QuestionTopicModel> questions;


    public TopicFeedbackModel(String topicName, ArrayList<QuestionTopicModel> questions){
        this.topicName = topicName;
        this.questions = questions;
    }

    public void setQuestions(ArrayList<QuestionTopicModel> questions) {
        this.questions = questions;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ArrayList<QuestionTopicModel> getQuestions() {
        return questions;
    }

    public String getTopicName() {
        return topicName;
    }
}
