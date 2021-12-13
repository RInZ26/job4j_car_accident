package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentTypeMem;
import ru.job4j.accident.repository.RuleMem;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;
    private final AccidentTypeMem accidentTypesMem;
    private final RuleMem ruleMem;

    @Autowired
    public AccidentService(AccidentMem accidentMem, AccidentTypeMem accidentTypesMem,
                           RuleMem ruleMem) {
        this.accidentMem = accidentMem;
        this.accidentTypesMem = accidentTypesMem;
        this.ruleMem = ruleMem;
    }

    private void changeAccident(int id, Accident accident) {
        accidentMem.change(id, accident);
    }

    public void saveAccident(Accident accident, String[] rIds) {
        AccidentType type = accident.getType();
        if (null != type) {
            accident.setType(findTypeById(type.getId()));
        }

        int[] parsedRIds = Arrays.stream(Optional.ofNullable(rIds).orElse(new String[0]))
                                 .mapToInt(Integer::parseInt).toArray();

        for (int ruleId : parsedRIds) {
            accident.addRule(findRuleById(ruleId));
        }

        int accidentId = accident.getId();
        if (0 == accidentId) {
            accidentMem.save(accident);
        } else {
            changeAccident(accidentId, accident);
        }
    }

    public List<Accident> findAllAccidents() {
        return accidentMem.findAll();
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findById(id);
    }

    public void updateAccidentByTypeId(int typeId, Accident accident) {
        accident.setType(findTypeById(typeId));
    }

    public List<AccidentType> findAllTypes() {
        return accidentTypesMem.findAll();
    }

    public AccidentType findTypeById(int id) {
        return accidentTypesMem.findById(id);
    }

    public List<Rule> findAllRules() {
        return ruleMem.findAll();
    }

    public Rule findRuleById(int id) {
        return ruleMem.findById(id);
    }
}
