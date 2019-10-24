package com.sbt.rnd.meetup2017.transport.impl.jmx;

import com.sbt.rnd.meetup2017.transport.impl.server.Server;

import java.util.List;

public class Statistic implements StatisticMBean {
    private List<Server> servers;

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public int getPoolSize() {
        System.out.println("HeartbeatMBeanImpl getPoolSize()");
        int res = 0;
        for(Server server : servers)
        {
            res += server.getServerState().getFreeThreadCount();
        }
        System.out.println("HeartbeatMBeanImpl getPoolSize()");
        return res;
    }

    @Override
    public int getPoolSize(String apiName) {
        System.out.println("HeartbeatMBeanImpl getPoolSize() for "+apiName);

        for(Server server : servers)
        {
            if (apiName.equals(server.getServerState().getApiName())) {

                int freeThreadCount = server.getServerState().getFreeThreadCount();
                System.out.println(server.getServerState().getApiName() +" free thread count:"+ freeThreadCount);
                return freeThreadCount;
            }
        }

        return 0;
    }
}
