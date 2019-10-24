package com.sbt.rnd.meetup2017.state;

import com.sbt.rnd.meetup2017.constants.GlobalSettings;
import com.sbt.rnd.meetup2017.state.model.ApiNodeState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerStateHolder {
    private static Map<String, Map<Date, ApiNodeState>> serverState;

    public static List<String> getApiListForNode(String nodeId) {
        List<String> apiList = new ArrayList<>();
        if (serverState != null) {
            for (Map.Entry<String, Map<Date, ApiNodeState>> stateMap : serverState.entrySet()) {
                if (stateMap.getKey().startsWith(nodeId)) {
                    long maxTime = 0;
                    String apiName = "";
                    for (ApiNodeState state : stateMap.getValue().values()) {
                        if (state.getDate().getTime() > maxTime) {
                            maxTime = state.getDate().getTime();
                            apiName = state.getApiName();
                        }
                    }
                    if (!apiName.isEmpty()
                            && maxTime > new Date().getTime() - GlobalSettings.API_VISIBILITY_DELAY) {
                        apiList.add(apiName);
                    }
                }
            }
        }
        return apiList;
    }

    public static Map<Date, ApiNodeState> getState(String nodeId, String apiName) {
        String key = nodeId + "." + apiName;
        return serverState.get(key);
    }

    public static void addState(ApiNodeState apiNodeState) {
        if (serverState == null) {
            serverState = new ConcurrentHashMap<>();
        }
        String key = apiNodeState.getNodeName() + "." + apiNodeState.getApiName();
        if (!serverState.containsKey(key)) {
            serverState.put(key, new ConcurrentHashMap<>());
        }
        Map<Date, ApiNodeState> nodeStates = serverState.get(key);
        nodeStates.put(apiNodeState.getDate(), apiNodeState);
    }
}
