package com.mycharge.trainingmanagementplatform.controller;


import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    public void userLogIn(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("usertype") int usertype){

        logService.login(username, password,usertype);

    }

    @PostMapping("/register")
    public void userRegister(@RequestParam("username") String username,@RequestParam("password") String password, @RequestParam("usertype") int usertype, @RequestParam("email") String email){

        logService.register(username, password, usertype, email);

    }

}
