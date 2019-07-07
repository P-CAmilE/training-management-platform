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

    @RequestMapping("/get")
    Result get(@Param("week")int week){
        return service.sum(week);
    }

    @RequestMapping("/weather")
    public String getDataByCityName(@Param("cityname") String cityName) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        String s=restTemplate.getForObject(uri,String.class,"广州");
//        return  s;

        //String uri="http://wthrcdn.etouch.cn/weather_mini?city="+cityName;

        URL url1 = null;
        URI uri = null;

        try {
            url1 = new URL(url);
            uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
        } catch (MalformedURLException|URISyntaxException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody=restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
        Object weatherResponse= JSONObject.parseObject(strbody,Object.class);
        return weatherResponse.toString();
    }
}
