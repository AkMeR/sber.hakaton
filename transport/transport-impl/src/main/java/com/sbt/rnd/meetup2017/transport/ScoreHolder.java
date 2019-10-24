package com.sbt.rnd.meetup2017.transport;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class ScoreHolder {
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicLong>> SCORE = new ConcurrentHashMap<>();

    public static Set<String> getApis() {
        return SCORE.keySet();
    }

    public static void update(String apiName, Map<String, Long> score) {
        ConcurrentHashMap<String, AtomicLong> newScore = new ConcurrentHashMap<>();
        for (Map.Entry<String, Long> scoreEntry : score.entrySet()) {
            newScore.put(scoreEntry.getKey(), new AtomicLong(scoreEntry.getValue()));
        }
        SCORE.put(apiName, newScore);
    }

    public static String getNodeId(String apiName) {
        ConcurrentHashMap<String, AtomicLong> score = ScoreHolder.getScore(apiName);
        String nodeId = getWeightedRandom(score.entrySet().stream(), new Random());

        if (nodeId == null) {
            return apiName;
        }

        score.get(nodeId).addAndGet(-1L);//TODO

        return nodeId;
    }

    public static void add(String apiName) {
        SCORE.put(apiName, new ConcurrentHashMap<>());
    }

    public static void remove(String apiName, String nodeId) {
        SCORE.get(apiName).remove(nodeId);
    }

    private static ConcurrentHashMap<String, AtomicLong> getScore(String apiName) {
        return SCORE.get(apiName);
    }

    private static <E> E getWeightedRandom(Stream<Map.Entry<E, AtomicLong>> weights, Random random) {
        return weights
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), -Math.log(random.nextLong()) / e.getValue().get()))
                .min(Comparator.comparing(AbstractMap.SimpleEntry::getValue))
                .map(AbstractMap.SimpleEntry::getKey)
                .orElse(null);
    }

}
