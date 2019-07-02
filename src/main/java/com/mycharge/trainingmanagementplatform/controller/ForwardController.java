package com.mycharge.trainingmanagementplatform.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class ForwardController {

    @RequestMapping("/{page}")
    public String forward(@PathVariable String page){
        return page;
    }


}