package com.example.androidfeedback.ui.feedback;

import com.example.androidfeedback.ui.question.QuestionTopicModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopicResult {
    @SerializedName("result")
    private ArrayList<TopicFeedbackModel> topicFeedbackModels;

    public TopicResult(ArrayList<TopicFeedbackModel> topicFeedbackModels){
        this.topicFeedbackModels = topicFeedbackModels;
    }

    public ArrayList<TopicFeedbackModel> getTopicFeedbackModels() {
        return topicFeedbackModels;
    }

    public void setTopicFeedbackModels(ArrayList<TopicFeedbackModel> topicFeedbackModels) {
        this.topicFeedbackModels = topicFeedbackModels;
    }
}
