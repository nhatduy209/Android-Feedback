package com.example.androidfeedback.ui.join;

public class JoinViewModel {
    private String message ;
    private boolean success ;
    private String traineeID;
    private String registrationCode;


    public JoinViewModel( String traineeID , String registrationCode ){
        this.traineeID = traineeID ;
        this.registrationCode = registrationCode;
    }
    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTraineeID() {
        return traineeID;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }
}
