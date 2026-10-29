package com.rasp.engine;

import com.rasp.core.model.Event;
import com.rasp.core.model.RiskResult;

public class SsrfRule implements Rule {
    @Override
    public String getRuleId() { return "SSRF-001"; }

    @Override
    public String getName() { return "SSRF"; }

    @Override
    public boolean matches(Event event) {
        if (event == null || event.getArgs() == null) {
            return false;
        }

        Object arg = event.getArgs().get("url");
        return arg != null && arg.toString().contains("http://169.254.169.254");
    }

    @Override
    public RiskResult evaluate(Event event) {
        RiskResult result = new RiskResult();
        result.setRuleId(getRuleId());
        result.setRuleName(getName());
        result.setSeverity("MEDIUM");
        result.setAction("alert");
        result.setScore(0.8);
        result.setReason("Potential SSRF to internal metadata endpoint detected");
        return result;
    }
}
