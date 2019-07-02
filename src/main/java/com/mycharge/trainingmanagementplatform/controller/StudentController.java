package com.mycharge.trainingmanagementplatform.controller;


import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @RequestMapping("/insert")
    public Result insert(@RequestBody MyObject myObject){
        return service.insert(myObject);
    }

    @RequestMapping("/find")
    public Result find(@RequestBody MyObject myObject){
        return service.find(myObject);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody MyObject myObject){
        return service.update(myObject);
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody MyObject myObject){
        return service.delete(myObject);
    }
}
