package com.sbt.rnd.meetup2017.state.model;

import java.util.Date;

public class ApiNodeState {
    private String nodeName;
    private String apiName;
    private Date date;
    private long heapUsage;
    private double cpuUsage;
    private int poolMax;
    private int poolUsage;

    public ApiNodeState() {
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public long getHeapUsage() {
        return heapUsage;
    }

    public void setHeapUsage(long heapUsage) {
        this.heapUsage = heapUsage;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public int getPoolMax() {
        return poolMax;
    }

    public void setPoolMax(int poolMax) {
        this.poolMax = poolMax;
    }

    public int getPoolUsage() {
        return poolUsage;
    }

    public void setPoolUsage(int poolUsage) {
        this.poolUsage = poolUsage;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
