package com.sbt.rnd.meetup2017.transport.impl.jmx;

public interface StatisticMBean {

    int getPoolSize();
    int getPoolSize(String apiName);

}
