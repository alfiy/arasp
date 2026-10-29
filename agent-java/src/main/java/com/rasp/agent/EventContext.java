package com.rasp.agent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class EventContext {
    private EventContext() {
    }

    public static Map<String, Object> buildContext(String hookPoint, String className, String methodName, Object[] args) {
        Map<String, Object> context = new HashMap<>();
        context.put("eventId", UUID.randomUUID().toString());
        context.put("hookPoint", hookPoint);
        context.put("className", className);
        context.put("methodName", methodName);
        context.put("args", args);
        context.put("timestamp", System.currentTimeMillis());
        return context;
    }
}
