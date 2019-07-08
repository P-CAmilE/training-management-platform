package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.TeamMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

@Service
public class GitServicelmpl implements GitService {

    @Autowired
    TeamMapper mapper;

    String api_url=" https://api.github.com/repos/{0}/stats/contributors";

    //所有队的总数据
    @Override
    public Result sum(int week){
        try {
            List<JSONObject> list = mapper.find(null);
            int sum_a=0,sum_d=0,sum_c=0;
            //遍历所有队伍
            for(JSONObject oj:list){
                String git =oj.getString("team_github");
                String[] strarray = git.split("github.com/");
                URL url1 = new URL(MessageFormat.format(api_url,strarray[1]));
                URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
                RestTemplate restTemplate=new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                String strbody=restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();

                //队内信息
                JSONArray jsonArray = JSONArray.parseArray(strbody);

                //遍历队员
                for(int i=0;i<jsonArray.size();i++) {
                    JSONObject stu = jsonArray.getJSONObject(i);
                    JSONArray week_ary=stu.getJSONArray("week");
                    if(week>=0) {
                        JSONObject week_oj = week_ary.getJSONObject(week);
                        sum_a += week_oj.getInteger("a");
                        sum_d += week_oj.getInteger("d");
                        sum_c += week_oj.getInteger("c");
                    }
                    //week为负数则表示查整个时间段，遍历所有周
                    else{
                        for(int j=0;j<week_ary.size();j++){
                            JSONObject week_oj = week_ary.getJSONObject(j);
                            sum_a += week_oj.getInteger("a");
                            sum_d += week_oj.getInteger("d");
                            sum_c += week_oj.getInteger("c");;
                        }
                    }
                }
            }
            Result res = Result.getResult(1);
            res.put("sum_a",sum_a);
            res.put("sum_d",sum_d);
            res.put("sum_c",sum_c);
            res.put("team_number",list.size());
            return res;
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    //获得一个队伍的a,b,c情况
    @Override
    public Result team(int team_id, int week){
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("team_id",team_id);

            JSONObject team = mapper.find(null).get(0);

            String git =team.getString("team_git");
            String[] strarray = git.split("github.com/");
            URL url1 = new URL(String.format(api_url,strarray[1]));
            URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            String strbody=restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
            //页面返回结果
            JSONArray jsonArray = JSONArray.parseArray(strbody);

            JSONArray data = new JSONArray();//队员adc信息存在data里
            Result res = Result.getResult(1);

            //遍历队员
            for(int i=0;i<jsonArray.size();i++) {
                JSONObject stu = jsonArray.getJSONObject(i);
                JSONArray week_ary=stu.getJSONArray("week");
                res.put("weeks",week_ary.size());//总共几周
                JSONObject adc=new JSONObject();//记录学生的adc信息
                int a=0,d=0,c=0;
                JSONObject week_oj;
                if(week>=0){
                    week_oj=stu.getJSONArray("week").getJSONObject(week);
                    a=week_oj.getInteger("a");
                    d=week_oj.getInteger("d");
                    c=week_oj.getInteger("c");
                }
                else{
                    ///遍历所有周
                    for(int j=0;j<week_ary.size();j++){
                        week_oj = week_ary.getJSONObject(j);
                        a += week_oj.getInteger("a");
                        d += week_oj.getInteger("d");
                        c += week_oj.getInteger("c");;
                    }
                    adc.put("a",a);
                    adc.put("d",d);
                    adc.put("c",c);
                    adc.put("username",stu.getJSONObject("author").getString("login"));
                    data.add(adc);
                }
            }


            res.put("data",data);
            return res;
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result language(int team_id) {
        try{
            String url=" https://api.github.com/repos/P-CAmilE/{0}/languages";

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("team_id",team_id);

            JSONObject team = mapper.find(null).get(0);

            String git =team.getString("team_git");
            String[] strarray = git.split("github.com/");
            URL url1 = new URL(String.format(api_url,strarray[1]));
            URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            String strbody=restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
            //页面返回结果
            JSONObject data = JSONObject.parseObject(strbody);

            Result res = Result.getResult(1);
            res.put("data",data);
            return res;
        }catch (MalformedURLException | URISyntaxException e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}

