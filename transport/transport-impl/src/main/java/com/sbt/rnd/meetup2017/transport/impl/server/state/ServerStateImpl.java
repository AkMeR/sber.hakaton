package com.sbt.rnd.meetup2017.transport.impl.server.state;

import com.sbt.rnd.meetup2017.transport.impl.server.RpcServerImpl;

public class ServerStateImpl implements ServerState {
    private String serverApiName;
    private RpcServerImpl server;

    public ServerStateImpl(String serverApiName, RpcServerImpl server) {
        this.serverApiName = serverApiName;
        this.server = server;
    }

    @Override
    public int getFreeThreadCount() {
        return server.getFreeThreadCount().get();
    }

    @Override
    public String getApiName() {
        return serverApiName;
    }

    @Override
    public void setApiName(String apiName) {
        this.serverApiName = apiName;
    }
}
