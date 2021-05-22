package com.example.androidfeedback.ui.question;

public class QuestionViewModel {
    private int questionID;
    private String questionContent;
    private int topicID;
    private String topicName;
    public QuestionViewModel(int questionID,String questionContent, int topicID, String topicName){
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.topicID = topicID;
        this.topicName = topicName;
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

    public String getTopicName() {
        return topicName;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setQuestionContent(String questionName) {
        this.questionContent = questionName;
    }

    public void setTopicName(String topicContent) {
        this.topicName = topicContent;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }
}
