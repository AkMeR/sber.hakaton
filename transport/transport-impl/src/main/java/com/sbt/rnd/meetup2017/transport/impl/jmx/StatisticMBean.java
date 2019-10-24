package com.sbt.rnd.meetup2017.transport.impl.jmx;

import java.util.Map;

public interface StatisticMBean {

    Map<String, Integer> getPoolSize();
    int getPoolSize(String apiName);

}
