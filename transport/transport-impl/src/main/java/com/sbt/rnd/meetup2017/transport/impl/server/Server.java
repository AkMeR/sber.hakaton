package com.sbt.rnd.meetup2017.transport.impl.server;

import com.sbt.rnd.meetup2017.transport.impl.server.state.ServerState;

public interface Server {

    void startServer();

    void stopServer();

    ServerState getServerState();
}
