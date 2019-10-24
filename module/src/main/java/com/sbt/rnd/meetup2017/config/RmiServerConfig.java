package com.sbt.rnd.meetup2017.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

@Configuration
public class RmiServerConfig {

//    @Value("${jmx.rmi.host:localhost}")
//    private String rmiHost;
//
//    @Value("${jmx.rmi.port}")
//    private Integer rmiPort;

    @Bean
    public RmiRegistryFactoryBean rmiRegistry() {
        final RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
        rmiRegistryFactoryBean.setPort(Integer.parseInt(System.getProperty("jmx.rmi.port")));
        rmiRegistryFactoryBean.setAlwaysCreate(true);
        return rmiRegistryFactoryBean;
    }

    @Bean
    @DependsOn("rmiRegistry")
    public ConnectorServerFactoryBean connectorServerFactoryBean() throws Exception {
        final ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
        connectorServerFactoryBean.setObjectName("connector:name=rmi");

        String serviceUrl = String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi",
                System.getProperty("jmx.rmi.host"),
                System.getProperty("jmx.rmi.port"),
                System.getProperty("jmx.rmi.host"),
                System.getProperty("jmx.rmi.port"));
        System.out.println("jmx service url: "+serviceUrl);
        connectorServerFactoryBean.setServiceUrl(serviceUrl);
        return connectorServerFactoryBean;
    }

}
