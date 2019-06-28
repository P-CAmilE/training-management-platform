package com.mycharge.trainingmanagementplatform.service;


public interface LogService {

    public int login(String username, String password, int usertype);

    public int register(String username, String password, int usertype, String email);


}
