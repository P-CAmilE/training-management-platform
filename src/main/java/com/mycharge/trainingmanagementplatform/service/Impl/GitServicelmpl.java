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

    //一周的总数据
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
                    JSONObject oj2 = jsonArray.getJSONObject(i);
                    if(week>=0) {
                        JSONObject week_oj = oj2.getJSONArray("weeks").getJSONObject(week);
                        sum_a += week_oj.getInteger("a");
                        sum_d += week_oj.getInteger("d");
                        sum_c += week_oj.getInteger("c");
                    }
                    //week为负数则表示查整个时间段，遍历所有周
                    else{
                        JSONArray week_oj = oj2.getJSONArray("weeks");
                        for(int j=0;j<week_oj.size();j++){
                            JSONObject oj3 = week_oj.getJSONObject(j);
                            sum_a += oj3.getInteger("a");
                            sum_d += oj3.getInteger("d");
                            sum_c += oj3.getInteger("c");;
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
    void team(int team_id,int week){
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("team_id",team_id);

            List<JSONObject> list = mapper.find(null);

            JSONObject data = new JSONObject();
            int sum_a,sum_d,sum_c;
            for(JSONObject oj:list){
                String git =oj.getString("team_git");
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

                for(int i=0;i<jsonArray.size();i++) {
                    JSONObject oj2 = jsonArray.getJSONObject(i);
                    oj2.getJSONArray("weeks");
                }
            }
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

