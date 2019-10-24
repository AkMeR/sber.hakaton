package com.sbt.rnd.meetup2017.services.interfaces;

import com.sbt.rnd.meetup2017.state.model.ApiNodeState;

import java.util.Date;
import java.util.Map;

public interface ScoreSolver {
    double compute(Map<Date, ApiNodeState> nodeStates);
}
