package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.List;

@Service
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypesMem;

    @Autowired
    public AccidentTypeService(AccidentTypeMem accidentTypesMem) {
        this.accidentTypesMem = accidentTypesMem;
    }

    public List<AccidentType> findAll() {
        return accidentTypesMem.findAll();
    }

    public void save(AccidentType accident) {
        if (0 == accident.getId()) {
            accidentTypesMem.save(accident);
        } else {
            changeAccident(accident.getId(), accident);
        }
    }

    public AccidentType findById(int id) {
        return accidentTypesMem.findById(id);
    }

    private void changeAccident(int id, AccidentType accident) {
        accidentTypesMem.change(id, accident);
    }
}
