package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.jdbc.AccidentJdbcTemplate;
import ru.job4j.accident.repository.jdbc.AccidentTypeJdbcTemplate;
import ru.job4j.accident.repository.jdbc.RuleJdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentStore;
    private final AccidentTypeJdbcTemplate accidentTypesStore;
    private final RuleJdbcTemplate ruleStore;

    @Autowired
    public AccidentService(AccidentJdbcTemplate accidentStore,
                           AccidentTypeJdbcTemplate accidentTypesStore,
                           RuleJdbcTemplate ruleStore) {
        this.accidentStore = accidentStore;
        this.accidentTypesStore = accidentTypesStore;
        this.ruleStore = ruleStore;
    }

    public void saveAccident(Accident accident, String[] rIds) {

        int[] parsedRIds = Arrays.stream(Optional.ofNullable(rIds).orElse(new String[0]))
                                 .mapToInt(Integer::parseInt).toArray();

        accident.setRules(Arrays.stream(parsedRIds).mapToObj(Rule::of).collect(Collectors.toSet()));

        int accidentId = accident.getId();
        if (0 == accidentId) {
            accidentStore.save(accident);
        } else {
            accidentStore.update(accident, accidentId);
        }
    }

    public List<Accident> findAllAccidents() {
        return accidentStore.findAll();
    }

    public Accident findAccidentById(int id) {
        return accidentStore.findById(id);
    }

    public List<AccidentType> findAllTypes() {
        return accidentTypesStore.findAll();
    }

    public List<Rule> findAllRules() {
        return ruleStore.findAll();
    }
}
