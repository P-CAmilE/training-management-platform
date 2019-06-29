package com.mycharge.trainingmanagementplatform.model;

public class Student {

    private String studentName;
    private String studentPassword;

    public Student(String studentName, String studentPassword) {
        this.studentName = studentName;
        this.studentPassword = studentPassword;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
