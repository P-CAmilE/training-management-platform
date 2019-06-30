package com.mycharge.trainingmanagementplatform.service;


import com.mycharge.trainingmanagementplatform.model.*;

public interface LogService {

    public User login(String username, String password, int usertype);

    public int register(String username, String password, int usertype, String email);


}
