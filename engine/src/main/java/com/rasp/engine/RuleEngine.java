package com.rasp.engine;

import com.rasp.core.model.Event;
import com.rasp.core.model.RiskResult;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {
    private final List<Rule> rules = new ArrayList<>();

    public RuleEngine() {
        rules.add(new SqlInjectionRule());
        rules.add(new CommandExecutionRule());
        rules.add(new SsrfRule());
        rules.add(new FileTraversalRule());
    }

    public List<RiskResult> evaluate(Event event) {
        List<RiskResult> results = new ArrayList<>();
        for (Rule rule : rules) {
            if (rule.matches(event)) {
                results.add(rule.evaluate(event));
            }
        }
        return results;
    }
}
