package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.*;
import com.mycharge.trainingmanagementplatform.model.*;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/index")
    public String  index(){
        return "home";
    }

    @GetMapping("/StudentIndex")
    public String studentIndex() {return "StudentIndex";}

    @GetMapping("/CompIndex")
    public String companyIndex() {return "CompIndex";}

//    @RequestParam("username") String username, @RequestParam("password") String password,
//    @RequestParam("usertype") int usertype

    @PostMapping("/login")
    public void logIn(@RequestBody JSONObject jsonObject){
//        jsonObject.getString("username"), jsonObject.getString("password"), jsonObject.getInteger("usertype")


        if (jsonObject.getInteger("usertype") == 0) {
            Student student = (Student)logService.login(jsonObject.getString("username"),
                    jsonObject.getString("password"), jsonObject.getInteger("usertype"));
            System.out.print(student.getStuAccount());
//            return "StudentIndex.html";
        }

        else if (jsonObject.getInteger("usertype") == 1) {
            Company company = (Company) logService.login(jsonObject.getString("username"),
                    jsonObject.getString("password"), jsonObject.getInteger("usertype"));
            System.out.print(company.getComAccount());
//            return "CompIndex.html";
        }

        else if (jsonObject.getInteger("usertype") == 2){
            Teacher teacher = (Teacher) logService.login(jsonObject.getString("username"),
                    jsonObject.getString("password"), jsonObject.getInteger("usertype"));
            System.out.print(teacher.getTeaAccount());
//            return "teacherIndex";
        }
//        else
//            return "error";
    }

    @PostMapping(value = "/register")
    public void userRegister(@RequestBody JSONObject jsonObject){

        logService.register(jsonObject.getString("username"), jsonObject.getString("password"),
                jsonObject.getInteger("usertype"), jsonObject.getString("useremail"));

//        return "StudentIndex.html";
    }

}
