package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.dto.AccidentDto;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeHibernate;
import ru.job4j.accident.repository.RuleHibernate;
import ru.job4j.accident.tools.DtoParser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;

    private final AccidentTypeHibernate accidentTypeStore;
    private final RuleHibernate ruleStore;
    private final DtoParser dtoParser;

    @Autowired
    public AccidentService(AccidentRepository accidentRepository,
                           AccidentTypeHibernate accidentTypeStore, RuleHibernate ruleStore,
                           DtoParser dtoParser) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeStore = accidentTypeStore;
        this.ruleStore = ruleStore;
        this.dtoParser = dtoParser;
    }

    public List<Accident> findAll() {
        return accidentRepository.findAll(Sort.by("id"));
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

    public void saveAccident(AccidentDto accidentDto, String[] rIds) {
        Accident accident = dtoParser.parseAccident(accidentDto);
        persistHelper(accident, rIds);
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id);
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
