package com.rasp.engine;

import com.rasp.core.model.Event;
import com.rasp.core.model.RiskResult;

public class FileTraversalRule implements Rule {
    @Override
    public String getRuleId() { return "FILE-001"; }

    @Override
    public String getName() { return "File Traversal"; }

    @Override
    public boolean matches(Event event) {
        if (event == null || event.getArgs() == null) {
            return false;
        }

        Object path = event.getArgs().get("path");
        return path != null && path.toString().contains("../");
    }

    @Override
    public RiskResult evaluate(Event event) {
        RiskResult result = new RiskResult();
        result.setRuleId(getRuleId());
        result.setRuleName(getName());
        result.setSeverity("HIGH");
        result.setAction("alert");
        result.setScore(0.86);
        result.setReason("Path traversal pattern detected in file access");
        return result;
    }
}
