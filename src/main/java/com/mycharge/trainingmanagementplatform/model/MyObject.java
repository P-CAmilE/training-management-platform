package com.mycharge.trainingmanagementplatform.model;

import java.util.HashMap;

//查询条件
public class MyObject extends HashMap<String, Object> {
    private MyObject(){}

    static public MyObject getObject(){
        MyObject myObject = new MyObject();
        //..
        return myObject;
    }
}