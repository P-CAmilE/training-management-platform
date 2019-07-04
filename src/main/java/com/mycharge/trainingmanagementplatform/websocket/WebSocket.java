package com.mycharge.trainingmanagementplatform.websocket;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.Session;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebSocket {
    protected static Integer onlineCount = 0;
    protected static CopyOnWriteArraySet<WebSocket> online = new CopyOnWriteArraySet<>();   //连接集合
    protected static CopyOnWriteArraySet<JSONObject> msg_list = new CopyOnWriteArraySet<>();//消息队列
    protected Session session ;
    protected String uid;


}
