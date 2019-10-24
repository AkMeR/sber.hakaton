package com.sbt.rnd.meetup2017.transport;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class ScoreHolder {
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicLong>> SCORE = new ConcurrentHashMap<>();

    public static String getNodeId(String apiName) {
        ConcurrentHashMap<String, AtomicLong> score = ScoreHolder.getScore(apiName);
        String nodeId = getWeightedRandom(score.entrySet().stream(), new Random());

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
                .orElseThrow(IllegalArgumentException::new).getKey();
    }

}
