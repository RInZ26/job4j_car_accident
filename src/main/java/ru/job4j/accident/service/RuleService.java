package ru.job4j.accident.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.List;

@Service
public class RuleService {
    private final RuleMem ruleMem;

    @Autowired
    public RuleService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    public List<Rule> findAll() {
        return ruleMem.findAll();
    }

    public void save(Rule rule) {
        if (0 == rule.getId()) {
            ruleMem.save(rule);
        } else {
            changeAccident(rule.getId(), rule);
        }
    }

    public Rule findById(int id) {
        return ruleMem.findById(id);
    }

    private void changeAccident(int id, Rule rule) {
        ruleMem.change(id, rule);
    }
}
