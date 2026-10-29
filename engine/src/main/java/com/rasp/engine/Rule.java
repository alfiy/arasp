package com.rasp.engine;

import com.rasp.core.model.Event;

public interface Rule {
    String getRuleId();
    String getName();
    boolean matches(Event event);
    String evaluate(Event event);
}
