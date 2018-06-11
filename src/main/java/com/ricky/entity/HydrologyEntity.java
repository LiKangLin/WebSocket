package com.ricky.entity;



/**
 * Created by PVer on 2018/5/12.
 */

public class HydrologyEntity {
    private Long id;  //id

    private String address;//地点

    private String nowtime;   //当前时间

    private String waterLevel;  //水位

    private String flow;    //流量  立方米/秒

    public String getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getNowtime() {
        return nowtime;
    }

    public void setNowtime(String nowtime) {
        this.nowtime = nowtime;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    @Override
    public String toString() {
        return "水保信息：" +
                nowtime  +
                 address  +
                "的水位为" + waterLevel+
                ", 流量为" + flow
                ;
    }
}
    /*@Override
    public String toString() {
        return "水保信息：" +
                ", time='" + nowtime + '\'' +
                ", address='" + address + '\'' +
                ", waterLever='" + waterLevel + '\'' +
                ", flow='" + flow + '\''
                ;
    }*/