package com.sbt.rnd.meetup2017.services;

import com.sbt.rnd.meetup2017.state.model.ApiNodeState;
import com.sbt.rnd.meetup2017.state.model.TopologyItem;
import org.springframework.stereotype.Service;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class NodePollServiceImpl {

    public List<ApiNodeState> pollNode(TopologyItem nodeInfo) {
        try {
            String serviceURL = "service:jmx:rmi:///jndi/rmi://localhost:" + nodeInfo.getJmxPort() + "/jmxrmi";
            JMXServiceURL url = new JMXServiceURL(serviceURL);
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            ObjectName memoryMBean = new ObjectName("java.lang:type=Memory");
            Object heapMemoryUsageAttribute = mbsc.getAttribute(memoryMBean, "HeapMemoryUsage");
            CompositeData heapMemoryUsageData = (CompositeData) heapMemoryUsageAttribute;
            Long committed = (Long) heapMemoryUsageData.get("committed");
            Long used = (Long) heapMemoryUsageData.get("used");
            Long max = (Long) heapMemoryUsageData.get("max");
            long heapUsagePercent = used * 100 / max;

            ObjectName osMBean = new ObjectName("java.lang:type=OperatingSystem");
            double cpuUsageAttribute = (double) mbsc.getAttribute(osMBean, "ProcessCpuLoad");

            //TODO
            List<String> apiList = new ArrayList<>();

            List<ApiNodeState> nodeStates = new ArrayList<>(apiList.size());

            for (String api : apiList) {
                ApiNodeState apiNodeState = new ApiNodeState();
                apiNodeState.setApiName(api);
                apiNodeState.setCpuUsage(cpuUsageAttribute);
                apiNodeState.setHeapUsage(heapUsagePercent);

                //TODO
                apiNodeState.setPoolMax(50);
                apiNodeState.setPoolUsage(new Random().nextInt(50));

                System.out.println(committed);
                System.out.println(max);
                System.out.println(cpuUsageAttribute);
            }
            return nodeStates;
        } catch (MalformedObjectNameException | IOException | ReflectionException | InstanceNotFoundException | AttributeNotFoundException | MBeanException e) {
            e.printStackTrace();
        }

        return null;
    }

}
