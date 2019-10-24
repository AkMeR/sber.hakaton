package com.sbt.rnd.meetup2017.transport.schedul;

import com.sbt.rnd.meetup2017.transport.ScoreHolder;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScoreScheduler {

    @Scheduled(fixedDelay = 30000, initialDelay = 1000)
    public void getScore() {
        Map<String, Map<String, Long>> score = getFromMl(ScoreHolder.getApis());

        for (Map.Entry<String, Map<String, Long>> entry : score.entrySet()) {
            ScoreHolder.update(entry.getKey(), entry.getValue());
        }
    }


    private Map<String, Map<String, Long>> getFromMl(Set<String> apiNames) {
        //TODO
        return new HashMap<>();
    }
}
