package com.example.androidfeedback.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedbackTraineeViewModel extends ViewModel {

    private String FeedbackTraineeTitle;
    private String TraineeClassID;
    private String TraineeClassName;
    private String TraineeModuleID;
    private String TraineeModuleName;
    private String TraineeEndTime;
    private boolean Status;
    public FeedbackTraineeViewModel(String FeedbackTraineeTitle, String TraineeClassID,
            String TraineeClassName,
            String TraineeModuleID,
            String TraineeModuleName,
            String TraineeEndTime,
            boolean Status) {
        this.FeedbackTraineeTitle= FeedbackTraineeTitle;
        this.Status = Status;
        this.TraineeClassID = TraineeClassID;
        this.TraineeClassName= TraineeClassName;
        this.TraineeEndTime = TraineeEndTime;
        this.TraineeModuleID = TraineeModuleID;
        this.TraineeModuleName = TraineeModuleName;

    }

    public String getFeedbackTraineeTitle() {
        return FeedbackTraineeTitle;
    }

    public String getTraineeClassID() {
        return TraineeClassID;
    }

    public String getTraineeClassName() {
        return TraineeClassName;
    }

    public String getTraineeEndTime() {
        return TraineeEndTime;
    }

    public String getTraineeModuleID() {
        return TraineeModuleID;
    }

    public String getTraineeModuleName() {
        return TraineeModuleName;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setFeedbackTraineeTitle(String feedbackTraineeTitle) {
        FeedbackTraineeTitle = feedbackTraineeTitle;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public void setTraineeClassID(String traineeClassID) {
        TraineeClassID = traineeClassID;
    }

    public void setTraineeClassName(String traineeClassName) {
        TraineeClassName = traineeClassName;
    }

    public void setTraineeEndTime(String traineeEndTime) {
        TraineeEndTime = traineeEndTime;
    }

    public void setTraineeModuleID(String traineeModuleID) {
        TraineeModuleID = traineeModuleID;
    }

    public void setTraineeModuleName(String traineeModuleName) {
        TraineeModuleName = traineeModuleName;
    }
}