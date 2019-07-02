package com.mycharge.trainingmanagementplatform.controller;


import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebJumpController {

    @RequestMapping("/{pageurl}")
    public String WebJump(@PathVariable("pageurl") String weburl) {
        return weburl + ".html";
    }

}
