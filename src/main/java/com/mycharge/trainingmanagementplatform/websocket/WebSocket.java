package com.mycharge.trainingmanagementplatform.websocket;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.Session;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebSocket {
    //todo:做好集合队列内存管理
    protected static Integer onlineCount = 0;
    protected static CopyOnWriteArraySet<WebSocket> online = new CopyOnWriteArraySet<>();   //连接集合
    protected static CopyOnWriteArraySet<JSONObject> msg_list = new CopyOnWriteArraySet<>();//消息队列
    //protected static final int maxsize=1000000;

    protected Session session ;
    protected String uid;


    //加入消息队列
    static protected void addMsg(JSONObject object){
//        if(msg_list.size()>maxsize){
//            //msg_list.remove();
//        }
        JSONObject oj=new JSONObject();
        oj.putAll(object);
        msg_list.add(oj);
    }
}
