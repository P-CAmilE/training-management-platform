package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/git")
public class GitController {
    String url=" https://api.github.com/repos/P-CAmilE/training-management-platform/stats/contributors";
    RestTemplate restTemplate=new RestTemplate();
    @RequestMapping("/get")
    void get(){


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    }

    @RequestMapping("/weather")
    public String getDataByCityName(@Param("cityname") String cityName) {
        String uri="http://wthrcdn.etouch.cn/weather_mini";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String s=restTemplate.getForObject(uri,String.class,"武汉");
        return  s;
    }
}
