package com.sbt.rnd.meetup2017.transport.schedul;

import com.sbt.rnd.meetup2017.transport.ScoreHolder;
import com.sbt.rnd.meetup2017.transport.api.NodeApiScore;
import com.sbt.rnd.meetup2017.transport.impl.jmx.StatisticMBean;
import org.springframework.scheduling.annotation.Scheduled;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.*;

public class ScoreScheduler {

    @Scheduled(fixedDelay = 30000, initialDelay = 1000)
    public void getScore() {
        Set<String> apiNames = ScoreHolder.getApis();
        if (apiNames == null || apiNames.isEmpty()) {
            return;
        }

        Map<String, Map<String, Long>> score = getFromMl(ScoreHolder.getApis());

        for (Map.Entry<String, Map<String, Long>> entry : score.entrySet()) {
            ScoreHolder.update(entry.getKey(), entry.getValue());
        }
    }


    private Map<String, Map<String, Long>> getFromMl(Set<String> apiNames) {
        Map<String, Map<String, Long>> res = new HashMap<>();
        try {
            String serviceUrl = String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi",
                    System.getProperty("jmx.rmi.host"),
                    System.getProperty("jmx.rmi.port"),
                    System.getProperty("jmx.rmi.host"),
                    System.getProperty("jmx.rmi.port"));

            JMXServiceURL url = new JMXServiceURL(serviceUrl);
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            //todo: скрестить с ML JMX сервером
            ObjectName scoringMlBean = new ObjectName("bean:name=statisticBean");
            StatisticMBean fooProxy = JMX.newMBeanProxy(mbsc, scoringMlBean, StatisticMBean.class);
            fooProxy.getPoolSize();

            //считаем что сходили в ML через jmx и получили список
            List<NodeApiScore> nodeInfos = getNodeInfos();
            res = createMap(nodeInfos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(res);
        return res;
    }

    private Map<String, Map<String, Long>> createMap(List<NodeApiScore> nodeInfos) {
        Map<String, Map<String, Long>> res = new HashMap<>();
        for (NodeApiScore nodeInfo : nodeInfos) {
            Map<String, Long> apiMap = res.get(nodeInfo.getApiName());
            if (apiMap == null) {
                apiMap = new HashMap<>();
            }
            apiMap.put(nodeInfo.getNodeId(), Math.round(nodeInfo.getScore()));
            res.put(nodeInfo.getApiName(), apiMap);
        }
        return res;
    }

    //todo: заглушечный метод - удалить когда скрестим
    private List<NodeApiScore> getNodeInfos() {
        List<NodeApiScore> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new NodeApiScore(String.valueOf(i),"com.sbt.rnd.meetup2017.transport.api.account.AccountApi", i * 10, 1.0));
        }
        for (int i = 0; i < 3; i++) {
            list.add(new NodeApiScore(String.valueOf(i),"com.sbt.rnd.meetup2017.transport.api.client.ClientApi", i * 10+5, 12.0));
        }
        return list;
    }


//    public static class NodeInfo {
//        String api;
//        String nodeId;
//        Long info;
//
//        public NodeInfo(String api, String nodeId, Long info) {
//            this.api = api;
//            this.nodeId = nodeId;
//            this.info = info;
//        }
//    }
}
