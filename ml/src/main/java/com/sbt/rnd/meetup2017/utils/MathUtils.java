package com.sbt.rnd.meetup2017.utils;

import java.util.Map;

public class MathUtils {
    public static Map<String, Double> normalize(Map<String, Double> dataset) {
        double summ = 0;
        for (Double value : dataset.values()) {
            summ = summ + value;
        }
        for (Map.Entry<String, Double> entry : dataset.entrySet()) {
            Double value = entry.getValue();
            double normalized = 1.0;
            if (summ != 0) {
                normalized = value / summ;
            }
            entry.setValue(normalized);
        }
        return dataset;
    }
}
