package ru.job4j.accident.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Rule {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    public static Rule of(int id) {
        Rule rule = new Rule();
        rule.id = id;
        return rule;
    }

    public static Rule of(int id, String name) {
        Rule rule = Rule.of(id);
        rule.name = name;
        return rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return id == rule.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
