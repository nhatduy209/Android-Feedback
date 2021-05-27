package com.example.androidfeedback.ui.feedback;

import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class FeedbackReviewModel {
    private int topicID;
    private ArrayList<QuestionViewModel> listQuestion;
    public FeedbackReviewModel(int topicID, ArrayList<QuestionViewModel> listQuestion){
        this.topicID = topicID;
        this.listQuestion = listQuestion;

    }

    public int getTopicID() {
        return topicID;
    }

    public ArrayList<QuestionViewModel> getListQuestion() {
        return listQuestion;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public void setListQuestion(ArrayList<QuestionViewModel> listQuestion) {
        this.listQuestion = listQuestion;
    }

}
