package ru.job4j.accident.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accident")
@NoArgsConstructor
public class Accident implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private AccidentType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id")
    @Getter
    @Setter
    private Set<Rule> rules = new HashSet<>();

    public static Accident of(int id, String name, String text, String address, AccidentType type) {
        Accident accident = of(name, text, address, type);
        accident.id = id;
        return accident;
    }

    public static Accident of(String name, String text, String address, AccidentType type) {
        Accident accident = new Accident();
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        return accident;
    }

    public static Accident of(String name, String text, String address, AccidentType type,
                              Set<Rule> rules) {
        Accident accident = of(name, text, address, type);
        accident.setRules(rules);
        accident.rules = rules;
        return accident;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}