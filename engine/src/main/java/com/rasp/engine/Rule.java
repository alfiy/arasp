package com.rasp.engine;

import com.rasp.core.model.Event;
import com.rasp.core.model.RiskResult;

public interface Rule {
    String getRuleId();
    String getName();
    boolean matches(Event event);
    RiskResult evaluate(Event event);
}
