package com.mycharge.trainingmanagementplatform.model;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//用来返回数据给前端，type指消息类型
public class Result extends HashMap<String, Object>{

    private Result(){}

    //指定消息类型
    static public Result getResult(int flag){
        Result res = new Result();
        switch (flag){
            case 0:res.put("type","fail");break;
            case 1:res.put("type","success");break;
            //...
        }
        return res;
    }

    //自定义消息类型
    static public Result getResult(String type){
        Result res = new Result();
        res.put("type",type);
        return res;
    }


    @Override
    public Result put(String key, Object value) {
        super.put(key,value);
        return this;
    }

    public Result put(String key, String value) {
        value = value.replaceAll("&","&amp;");
        value = value.replaceAll("<","&lt;");
        value = value.replaceAll(">","&gt;");
        super.put(key,value);
        return this;
    }

    public Result put(String key, List<JSONObject> list) {
        List<JSONObject> l2=new LinkedList<>();
        for(JSONObject oj:list){
            String value=oj.toJSONString();
            value = value.replaceAll("&","&amp;");
            value = value.replaceAll("<","&lt;");
            value = value.replaceAll(">","&gt;");
            oj=JSONObject.parseObject(value);
            l2.add(oj);
        }
        super.put(key,l2);
        return this;
    }

}
