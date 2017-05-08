package com.mami.bean;

import java.util.Date;

public class Groupup {
    private Integer id;

    private Date growDate;

    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGrowDate() {
        return growDate;
    }

    public void setGrowDate(Date growDate) {
        this.growDate = growDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}