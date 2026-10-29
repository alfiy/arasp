package com.rasp.engine;

import com.rasp.core.model.Event;
import com.rasp.core.model.RiskResult;

import java.util.regex.Pattern;

public class SqlInjectionRule implements Rule {
    private static final Pattern SQLI_PATTERN = Pattern.compile("(?i)(union\\s+select|or\\s+1=1|sleep\\s*\\(|--|#|';\\s*--)");

    @Override
    public String getRuleId() { return "SQLI-001"; }

    @Override
    public String getName() { return "SQL Injection"; }

    @Override
    public boolean matches(Event event) {
        if (event == null || event.getArgs() == null) {
            return false;
        }
        Object sql = event.getArgs().get("args");
        if (sql == null) {
            return false;
        }
        String sqlText = sql.toString();
        return SQLI_PATTERN.matcher(sqlText).find();
    }

    @Override
    public RiskResult evaluate(Event event) {
        RiskResult result = new RiskResult();
        result.setRuleId(getRuleId());
        result.setRuleName(getName());
        result.setSeverity("HIGH");
        result.setAction("alert");
        result.setScore(0.92);
        result.setReason("Pattern matched SQL injection indicators in SQL execution path");
        return result;
    }
}
