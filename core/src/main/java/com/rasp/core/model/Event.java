package com.rasp.core.model;

import java.time.Instant;
import java.util.Map;

public class Event {
    private String eventId;
    private String appName;
    private String env;
    private String requestId;
    private String traceId;
    private Instant timestamp;
    private String hookPoint;
    private String className;
    private String methodName;
    private String url;
    private String controller;
    private String action;
    private Map<String, Object> args;
    private String stackTrace;
    private Map<String, Object> context;
    private String riskHints;
    private String severity;
    private String status;

    public Event() {
    }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getAppName() { return appName; }
    public void setAppName(String appName) { this.appName = appName; }

    public String getEnv() { return env; }
    public void setEnv(String env) { this.env = env; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public String getHookPoint() { return hookPoint; }
    public void setHookPoint(String hookPoint) { this.hookPoint = hookPoint; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getController() { return controller; }
    public void setController(String controller) { this.controller = controller; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public Map<String, Object> getArgs() { return args; }
    public void setArgs(Map<String, Object> args) { this.args = args; }

    public String getStackTrace() { return stackTrace; }
    public void setStackTrace(String stackTrace) { this.stackTrace = stackTrace; }

    public Map<String, Object> getContext() { return context; }
    public void setContext(Map<String, Object> context) { this.context = context; }

    public String getRiskHints() { return riskHints; }
    public void setRiskHints(String riskHints) { this.riskHints = riskHints; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
