package com.mycharge.trainingmanagementplatform.model;

import java.util.HashMap;

public class MSG extends HashMap<String, Object> {

    static  public MSG newMessage(){
        MSG msg = new MSG();
        return msg;
    }

    static public MSG newMessage(String key, Object object){
        MSG msg = new MSG();
        msg.put(key,object);
        return msg;
    }
}
