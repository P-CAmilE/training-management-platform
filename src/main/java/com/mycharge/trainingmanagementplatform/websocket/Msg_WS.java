package com.mycharge.trainingmanagementplatform.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

//一对一
@Component
@ServerEndpoint(value = "/websocket/{uid}")
public class Msg_WS extends WebSocket{
    static Logger log= LoggerFactory.getLogger(Msg_WS.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid){
        try {
            log.info("连接开启");
            this.uid = uid;
            this.session = session;
            online.add(this);     //加入set中  记录所有长连接的对象
            addOnlineCount();           //在线数加1
            log.info("有新窗口开始监听:" + uid + ",当前在线人数为" + getOnlineCount());
            RemoteEndpoint.Basic br = session.getBasicRemote();
            //查看消息队列有没有自己的消息
            for (JSONObject oj : msg_list) {
                if (oj.getString("to").equals(uid)) {
                    br.sendText(oj.toJSONString());
                    msg_list.remove(oj);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当前对象关闭后，移除缓存堆栈中的数据对象，并减少连接数
     */
    @OnClose
    public void onClose() {
        online.remove(this);
        subOnlineCount();//在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount()+"离线用户ID"+this.uid);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 根据项目要求设计收到消息后的逻辑
     * @param uid
     * @param info
     * @param session
     */
    @OnMessage
    public void onMessage(@PathParam("uid")String uid, String info, Session session){
        try {
            JSONObject object=JSON.parseObject(info);
            String to = object.getString("to");
            if(to==null) {
                log.info("to字段为空，无用消息");
            return;
            }
            String from = object.getString("from");
            String msg = object.getString("msg");

            log.info(object.toJSONString());

            //发送给to指定的用户，如果不在线则把消息存到消息队列
            Boolean is_online=false;
            for (WebSocket webSocketServer : online) {
                if (webSocketServer.uid.equals(to)) {
                    webSocketServer.session.getBasicRemote().sendText(object.toJSONString());
                    is_online=true;
                }
            }
            if(!is_online){
                msg_list.add(object);
            }
        } catch (JSONException | IOException e) {
        e.printStackTrace();
    }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        Msg_WS.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        Msg_WS.onlineCount--;
    }
}

