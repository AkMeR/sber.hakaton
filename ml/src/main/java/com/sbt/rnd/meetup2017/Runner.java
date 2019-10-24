package com.sbt.rnd.meetup2017;

import com.sbt.rnd.meetup2017.state.TransportTopologyHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-beans.xml");
        TransportTopologyHolder.addNodeInfo("server1", "127.0.0.1", "1234");
//        ServerStateHolder.addServerInfo("server2", "127.0.0.1", "1036");

        System.out.println("ML module started...");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }
}
