package com.example.androidfeedback.ui.uiclass;

public class ClassViewModel {
    private String  classId;
    private String className;
    private String startDate;
    private String endDate;
    private String Capacity;
    public ClassViewModel( String classId,
                           String className,
                           String startDate,
                           String endDate,
                           String Capacity
    ){
        this.classId = classId;
        this.className=className;
        this.startDate = startDate;
        this.endDate =endDate;
        this.Capacity = Capacity;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}
