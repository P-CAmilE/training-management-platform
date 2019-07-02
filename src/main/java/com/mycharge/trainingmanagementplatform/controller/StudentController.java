package com.mycharge.trainingmanagementplatform.controller;


import com.mycharge.trainingmanagementplatform.service.LogService;
import com.mycharge.trainingmanagementplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private StudentService sudentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(){
        return "home";
    }

    @RequestMapping(value = "/StudentIndex",method = RequestMethod.GET)
    public String studentIndex(HttpServletRequest request){
        String userName = null;
        String userType = null;
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("username")){
                userName = cookie.getValue();
            } else {
                userType = cookie.getValue();
            }
        }


    }

}
