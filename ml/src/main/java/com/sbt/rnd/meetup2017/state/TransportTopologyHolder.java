package com.sbt.rnd.meetup2017.state;

import com.sbt.rnd.meetup2017.state.model.ApiNodeInfo;
import com.sbt.rnd.meetup2017.state.model.TopologyItem;

import java.util.ArrayList;
import java.util.List;

public class TransportTopologyHolder {
    private static List<TopologyItem> nodeList;
    private static List<ApiNodeInfo> apiNodeInfo;

    public static ApiNodeInfo getApiNodeInfo(String nodeId, String apiName) {
        for (ApiNodeInfo nodeInfo : apiNodeInfo) {
            if (nodeInfo.getNodeId().equalsIgnoreCase(nodeId)
                    && nodeInfo.getApiName().equalsIgnoreCase(apiName)) {
                return nodeInfo;
            }
        }
        return null;
    }

    public static void setApiNodeInfo(String nodeId, String apiName, double score, double delta) {
        if (apiNodeInfo == null) {
            apiNodeInfo = new ArrayList<>();
        }
        ApiNodeInfo apiNodeInfoItem = new ApiNodeInfo();
        boolean found = false;
        for (ApiNodeInfo nodeInfo : apiNodeInfo) {
            if (nodeInfo.getNodeId().equalsIgnoreCase(nodeId)
                    && nodeInfo.getApiName().equalsIgnoreCase(apiName)) {
                apiNodeInfoItem = nodeInfo;
                found = true;
            }
        }
        apiNodeInfoItem.setScore(score);
        apiNodeInfoItem.setDelta(delta);
        if (!found) {
            apiNodeInfo.add(apiNodeInfoItem);
        }
    }

    public static void addNodeInfo(String name, String ip, String port) {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        nodeList.add(new TopologyItem(name, ip, port));
    }


    public static List<TopologyItem> getNodeList() {
        return nodeList;
    }

}
