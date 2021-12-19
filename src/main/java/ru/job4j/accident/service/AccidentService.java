package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.accident.dao.repository.AccidentRepository;
import ru.job4j.accident.dao.repository.AccidentTypeRepository;
import ru.job4j.accident.dao.repository.RuleRepository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;
    private final AccidentTypeRepository typeRepository;

    @Autowired
    public AccidentService(AccidentRepository accidentRepository, RuleRepository ruleRepository,
                           AccidentTypeRepository typeRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
        this.typeRepository = typeRepository;
    }

    public List<Accident> findAll() {
        return accidentRepository.findAll(Sort.by("id"));
    }

    private void persistHelper(Accident accident, String[] rIds) {
        AccidentType type = accident.getType();
        if (null != type) {
            accident.setType(findTypeById(type.getId()));
        }

        Arrays.stream(Optional.ofNullable(rIds)
                              .orElse(new String[0]))
              .mapToInt(Integer::parseInt)
              .forEach(id -> accident.addRule(Rule.of(id, null)));
    }

    public void saveAccident(Accident accident, String[] rIds) {
        persistHelper(accident, rIds);
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id);
    }

    public List<AccidentType> findAllTypes() {
        return typeRepository.findAll();
    }

    public AccidentType findTypeById(int id) {
        return typeRepository.findById(id);
    }

    public List<Rule> findAllRules() {
        return ruleRepository.findAll();
    }
}
