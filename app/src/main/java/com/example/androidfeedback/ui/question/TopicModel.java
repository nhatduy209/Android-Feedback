package com.example.androidfeedback.ui.question;

import androidx.annotation.NonNull;

public class TopicModel {

    String topicName ;

    int topicID ;

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getTopicID() {
        return topicID;
    }

    public TopicModel(String topicName, int topicID) {
        this.topicName = topicName;
        this.topicID = topicID;
    }

    @NonNull
    @Override
    public String toString() {
        return topicName;
    }
}
