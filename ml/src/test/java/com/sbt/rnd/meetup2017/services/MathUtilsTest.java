package com.sbt.rnd.meetup2017.services;

import com.sbt.rnd.meetup2017.utils.MathUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class MathUtilsTest {

    @Test
    public void normalizeEquals() {
        HashMap<String, Double> dataSet = new HashMap<>();
        dataSet.put("node1", 0.5);
        dataSet.put("node2", 0.5);
        dataSet.put("node3", 0.5);
        Map<String, Double> normalized = MathUtils.normalize(dataSet);
        Double node1 = normalized.get("node1");
        Double node2 = normalized.get("node2");
        Double node3 = normalized.get("node3");
        assertEquals(0.3333, node1, 0.03);
        assertEquals(0.3333, node2, 0.03);
        assertEquals(0.3333, node3, 0.03);
    }

    @Test
    public void normalize532() {
        HashMap<String, Double> dataSet = new HashMap<>();
        dataSet.put("node1", 0.5);
        dataSet.put("node2", 0.3);
        dataSet.put("node3", 0.2);
        Map<String, Double> normalized = MathUtils.normalize(dataSet);
        Double node1 = normalized.get("node1");
        Double node2 = normalized.get("node2");
        Double node3 = normalized.get("node3");
        assertEquals(0.5, node1, 0.03);
        assertEquals(0.3, node2, 0.03);
        assertEquals(0.2, node3, 0.03);
    }
}