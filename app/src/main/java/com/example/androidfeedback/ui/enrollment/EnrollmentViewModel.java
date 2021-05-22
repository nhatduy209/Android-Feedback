package com.example.androidfeedback.ui.enrollment;

public class EnrollmentViewModel {
    private String TraineeID;
    private String TrainerName;
    private String ClassID;
    private String ClassName;
    public EnrollmentViewModel(String TraineeID,String TraineeName, String ClassID, String ClassName){
        this.TraineeID = TraineeID;
        this.ClassName = ClassName;
        this.ClassID = ClassID;
        this.TrainerName = TraineeName;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setTraineeID(String traineeID) {
        TraineeID = traineeID;
    }

    public void setTrainerName(String trainerName) {
        TrainerName = trainerName;
    }

    public String getClassID() {
        return ClassID;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getTraineeID() {
        return TraineeID;
    }

    public String getTrainerName() {
        return TrainerName;
    }
}
