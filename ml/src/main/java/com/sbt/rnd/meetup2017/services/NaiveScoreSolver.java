package com.sbt.rnd.meetup2017.services;

import com.sbt.rnd.meetup2017.services.interfaces.ScoreSolver;
import com.sbt.rnd.meetup2017.state.model.ApiNodeState;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

@Component
public class NaiveScoreSolver implements ScoreSolver {
    @Override
    public double compute(Map<Date, ApiNodeState> nodeStates) {
        ApiNodeState apiNodeState = nodeStates.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getKey))
                .get()
                .getValue();
        int score = 1 - apiNodeState.getPoolUsage() / apiNodeState.getPoolMax();
        return score;
    }
}
