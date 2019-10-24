package com.sbt.rnd.meetup2017.transport.impl.server.state;

public interface ServerState {
    int getFreeThreadCount();
    String getApiName();
    void setApiName(String apiName);
}
