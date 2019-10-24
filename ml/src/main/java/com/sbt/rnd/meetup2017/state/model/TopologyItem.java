package com.sbt.rnd.meetup2017.state.model;

public class TopologyItem {
    private String nodeId;
    private String serverIp;
    private String jmxPort;

    public TopologyItem(String nodeId, String serverIp, String jmxPort) {
        this.nodeId = nodeId;
        this.serverIp = serverIp;
        this.jmxPort = jmxPort;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(String jmxPort) {
        this.jmxPort = jmxPort;
    }
}
