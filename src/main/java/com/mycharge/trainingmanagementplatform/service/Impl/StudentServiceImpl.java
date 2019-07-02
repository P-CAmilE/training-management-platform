package com.mycharge.trainingmanagementplatform.service.Impl;

import com.mycharge.trainingmanagementplatform.model.MSG;
import com.mycharge.trainingmanagementplatform.service.StudentService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class StudentServiceImpl implements StudentService {



    @Override
    public MSG SutdentInfo(HttpSession session) {


        MSG msg = MSG.newMessage("","");
        return msg;
    }
}
