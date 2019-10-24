package com.sbt.rnd.meetup2017.transport.api;

public class NodeApiScore {
    private String nodeId;
    private String apiName;
    private double score;
    private double delta;

    public NodeApiScore(String nodeId, String apiName, double score, double delta) {
        this.nodeId = nodeId;
        this.apiName = apiName;
        this.score = score;
        this.delta = delta;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
}
