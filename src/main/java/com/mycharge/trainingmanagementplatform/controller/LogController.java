package com.mycharge.trainingmanagementplatform.controller;

import com.mycharge.trainingmanagementplatform.model.*;
import com.mycharge.trainingmanagementplatform.service.LogService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@CrossOrigin
@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    @ResponseBody
    public MSG logIn(@RequestBody MyObject myObject, HttpServletResponse response){



        MSG loginMSG = logService.login((String)myObject.get("username"),
                (String)myObject.get("password"), (Integer) myObject.get("usertype"));

        Cookie accountCookie = new Cookie("useraccount",(String) loginMSG.get("useraccount"));

        accountCookie.setPath("/usercookie");

        response.addCookie(accountCookie);

        return loginMSG;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public MSG userRegister(@RequestBody MyObject myObject){
        MSG registerMSG = logService.register((String)myObject.get("username"), (String)myObject.get("password"),
                (Integer) myObject.get("usertype"), (String)myObject.get("useremail"));
        return registerMSG;
    }
}
