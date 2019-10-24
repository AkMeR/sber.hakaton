package com.sbt.rnd.meetup2017.transport.config;

import com.sbt.rnd.meetup2017.transport.api.RequestTransportProxyFactory;
import com.sbt.rnd.meetup2017.transport.impl.RequestTransportProxyFactoryImpl;
import com.sbt.rnd.meetup2017.transport.schedul.ScoreScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransportConfig {

    @Bean
    RequestTransportProxyFactory transportProxyFactory() {
        return new RequestTransportProxyFactoryImpl();
    }

    @Bean
    ScoreScheduler scoreScheduler() {
        return new ScoreScheduler();
    }

}
