package com.sbt.rnd.meetup2017.transport.impl.jmx;

import com.sbt.rnd.meetup2017.transport.impl.server.Server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistic implements StatisticMBean {
    private List<Server> servers;

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public Map<String, Integer> getPoolSize() {
        Map<String, Integer> hashMap = new HashMap<>(servers.size());
        for(Server server : servers)
        {
            hashMap.put(server.getServerState().getApiName(), server.getServerState().getFreeThreadCount());
        }
        System.out.println("HeartbeatMBeanImpl getPoolSize for all api: "+hashMap);
        return hashMap;
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
