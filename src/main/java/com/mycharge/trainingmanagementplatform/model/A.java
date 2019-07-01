package com.mycharge.trainingmanagementplatform.model;

import java.io.Serializable;

public class A implements Serializable {
    private Integer aid;
    private String aname;

    public Integer getAid() {
        return aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }
}
