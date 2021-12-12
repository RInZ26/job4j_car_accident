package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {
    /**
     * Делаем вид, что у нас нет ORM и id мы проставляем сами
     */
    private final AtomicInteger identityCounter = new AtomicInteger(0);

    private final Map<Integer, Rule> rules = new HashMap<>(
            Map.of(identityCounter.addAndGet(1), Rule.of(1, "Статья - казнить"),
                    identityCounter.addAndGet(1), Rule.of(2, "Статья - отпустить"),
                    identityCounter.addAndGet(1), Rule.of(3, "Статья - запятая")));

    public void save(Rule rule) {
        change(identityCounter.addAndGet(1), rule);
    }

    public void change(int key, Rule rule) {
        rules.put(key, rule);
    }

    public List<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }

    public Rule findById(int id) {
        return rules.get(id);
    }
}
