package com.mycharge.trainingmanagementplatform.controller;


import com.mycharge.trainingmanagementplatform.service.LogService;
import com.mycharge.trainingmanagementplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String studentIndex(HttpServletRequest request, HttpSession session){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String tname=logService.login(username,password);
        session.setAttribute("tname",tname);
        if(tname==null){
            return "home";
        }else {
            return "StudentIndex";
        }
    }

}
