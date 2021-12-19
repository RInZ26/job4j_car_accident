package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.orm.AccidentHibernate;
import ru.job4j.accident.repository.orm.AccidentTypeHibernate;
import ru.job4j.accident.repository.orm.RuleHibernate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentHibernate accidentStore;
    private final AccidentTypeHibernate accidentTypeStore;
    private final RuleHibernate ruleStore;

    @Autowired
    public AccidentService(AccidentHibernate accidentStore, AccidentTypeHibernate accidentTypeStore,
                           RuleHibernate ruleStore) {
        this.accidentStore = accidentStore;
        this.accidentTypeStore = accidentTypeStore;
        this.ruleStore = ruleStore;
    }

    private void persistHelper(Accident accident, String[] rIds) {
        AccidentType type = accident.getType();
        if (null != type) {
            accident.setType(findTypeById(type.getId()));
        }

        int[] parsedRIds = Arrays.stream(Optional.ofNullable(rIds).orElse(new String[0]))
                                 .mapToInt(Integer::parseInt).toArray();

        for (int ruleId : parsedRIds) {
            accident.addRule(findRuleById(ruleId));
        }
    }

    public void saveAccident(Accident accident, String[] rIds) {
        persistHelper(accident, rIds);
        accidentStore.save(accident);
    }

    public void updateAccident(Accident accident, String[] rIds) {
        persistHelper(accident, rIds);
        accidentStore.update(accident);
    }

    public List<Accident> findAllAccidents() {
        return accidentStore.findAll();
    }

    public Accident findAccidentById(int id) {
        return accidentStore.findById(id);
    }

    public List<AccidentType> findAllTypes() {
        return accidentTypeStore.findAll();
    }

    public AccidentType findTypeById(int id) {
        return accidentTypeStore.findById(id);
    }

    public List<Rule> findAllRules() {
        return ruleStore.findAll();
    }

    public Rule findRuleById(int id) {
        return ruleStore.findById(id);
    }
}
