package com.sbt.rnd.meetup2017.scheduler;

import com.sbt.rnd.meetup2017.services.interfaces.ScoreSolver;
import com.sbt.rnd.meetup2017.state.ServerStateHolder;
import com.sbt.rnd.meetup2017.state.TransportTopologyHolder;
import com.sbt.rnd.meetup2017.state.model.ApiNodeState;
import com.sbt.rnd.meetup2017.state.model.TopologyItem;
import com.sbt.rnd.meetup2017.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ComputeScoreTask {
    @Autowired
    ScoreSolver scoreSolver;

    public void run() {
        List<TopologyItem> serverList = TransportTopologyHolder.getNodeList();
        if (serverList != null) {
            Map<String, Double> scores = new HashMap<>();
            for (TopologyItem nodeInfo : serverList) {
                List<String> apiList = ServerStateHolder.getApiListForNode(nodeInfo.getNodeId());
                for (String apiName : apiList) {
                    Map<Date, ApiNodeState> state =
                            ServerStateHolder.getState(nodeInfo.getNodeId(), apiName);
                    double score = scoreSolver.compute(state);
                    String key = nodeInfo.getNodeId() + "." + apiName;
                    scores.put(key, score);
                }
            }
            MathUtils.normalize(scores);
            for (TopologyItem nodeInfo : serverList) {
                List<String> apiList = ServerStateHolder.getApiListForNode(nodeInfo.getNodeId());
                for (String apiName : apiList) {
                    String key = nodeInfo.getNodeId() + "." + apiName;
                    Double score = scores.get(key);
                    TransportTopologyHolder.setApiNodeInfo(nodeInfo.getNodeId(), apiName, score, 1.0);
                }
            }
        }
    }
}
