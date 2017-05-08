package com.mami.bean;

public class Vaccine {
    private Integer id;

    private String name;

    private Integer freqNum;

    private String freqUnit;

    private Integer age;

    private String usage;

    private Integer state;
    
    private String intro;

    private String atten;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getAtten() {
        return atten;
    }

    public void setAtten(String atten) {
        this.atten = atten == null ? null : atten.trim();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFreqNum() {
        return freqNum;
    }

    public void setFreqNum(Integer freqNum) {
        this.freqNum = freqNum;
    }

    public String getFreqUnit() {
        return freqUnit;
    }

    public void setFreqUnit(String freqUnit) {
        this.freqUnit = freqUnit == null ? null : freqUnit.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage == null ? null : usage.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}