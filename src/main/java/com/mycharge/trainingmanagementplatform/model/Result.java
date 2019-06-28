package com.mycharge.trainingmanagementplatform.model;

import java.util.HashMap;

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
}
