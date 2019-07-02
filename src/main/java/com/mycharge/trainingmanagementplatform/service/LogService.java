package com.mycharge.trainingmanagementplatform.service;


import com.mycharge.trainingmanagementplatform.model.*;

public interface LogService {

    public MSG login(String username, String password, int usertype);

    public MSG register(String username, String password, int usertype, String email);


}
