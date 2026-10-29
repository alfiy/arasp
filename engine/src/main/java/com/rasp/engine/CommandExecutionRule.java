package com.rasp.engine;

import com.rasp.core.model.Event;
import com.rasp.core.model.RiskResult;

import java.util.regex.Pattern;

public class CommandExecutionRule implements Rule {
    private static final Pattern CMD_PATTERN = Pattern.compile("(?i)(;|&&|\\|\\||/bin/sh|cmd\\.|powershell|curl\\s+|wget\\s+|bash\s+-c)");

    @Override
    public String getRuleId() { return "CMD-001"; }

    @Override
    public String getName() { return "Command Execution"; }

    @Override
    public boolean matches(Event event) {
        if (event == null || event.getArgs() == null) {
            return false;
        }

        Object payload = event.getArgs().get("args");
        if (payload == null) {
            return false;
        }

        return CMD_PATTERN.matcher(payload.toString()).find();
    }

    @Override
    public RiskResult evaluate(Event event) {
        RiskResult result = new RiskResult();
        result.setRuleId(getRuleId());
        result.setRuleName(getName());
        result.setSeverity("HIGH");
        result.setAction("alert");
        result.setScore(0.95);
        result.setReason("Command execution with suspicious shell or command chaining was observed");
        return result;
    }
}
