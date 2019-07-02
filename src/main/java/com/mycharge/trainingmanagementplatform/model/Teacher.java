package com.mycharge.trainingmanagementplatform.model;

public class Teacher implements  User{

    private String teaName;
    private String teaAccount;
    private String teaEmail;

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaAccount() {
        return teaAccount;
    }

    public void setTeaAccount(String teaAccount) {
        this.teaAccount = teaAccount;
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail;
    }

    @Override
    public String getUserAccount() {
        return this.teaAccount;
    }
}
