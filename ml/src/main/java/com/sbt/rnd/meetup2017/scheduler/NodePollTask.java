package com.sbt.rnd.meetup2017.scheduler;

import com.sbt.rnd.meetup2017.services.NodePollServiceImpl;
import com.sbt.rnd.meetup2017.state.ServerStateHolder;
import com.sbt.rnd.meetup2017.state.TransportTopologyHolder;
import com.sbt.rnd.meetup2017.state.model.ApiNodeState;
import com.sbt.rnd.meetup2017.state.model.TopologyItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NodePollTask {
    private static final Logger LOGGER = LogManager.getLogger(NodePollTask.class.getName());

    @Autowired
    private NodePollServiceImpl serverPollService;

    public void getServerStats() {
        List<TopologyItem> serverList = TransportTopologyHolder.getNodeList();
        if (serverList != null) {
            for (TopologyItem nodeInfo : serverList) {
                List<ApiNodeState> nodeStates = serverPollService.pollNode(nodeInfo);
                if (nodeStates != null) {
                    for (ApiNodeState nodeState : nodeStates) {
                        ServerStateHolder.addState(nodeState);
                    }
                }
            }
        }
    }

}
