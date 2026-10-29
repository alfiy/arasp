package com.rasp.core.model;

import java.time.Instant;
import java.util.Map;

public class RiskResult {
    private String ruleId;
    private String ruleName;
    private String severity;
    private String action;
    private double score;
    private String reason;
    private Instant createdAt;

    public RiskResult() {
        this.createdAt = Instant.now();
    }

    public String getRuleId() { return ruleId; }
    public void setRuleId(String ruleId) { this.ruleId = ruleId; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
