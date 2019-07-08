package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.GitService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/git")
public class GitController {
    String url=" https://api.github.com/repos/P-CAmilE/training-management-platform/stats/contributors";
    RestTemplate restTemplate=new RestTemplate();

    @Autowired
    GitService service;

    @RequestMapping("/sum_adc")
    Result sum(@Param("week")int week){
        return service.sum(week);
    }

    @RequestMapping("/team_adc")
    Result  team(@Param("team_id") int team_id,@Param("week")int week) {
        return service.team(team_id,week);
    }
}
