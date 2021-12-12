package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    @Autowired
    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> findAll() {
        return accidentMem.getAllAccidents();
    }

    public void save(Accident accident) {
        if (0 == accident.getId()) {
            accidentMem.addAccident(accident);
        } else {
            changeAccident(accident.getId(), accident);
        }
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    private void changeAccident(int id, Accident accident) {
        accidentMem.changeAccident(id, accident);
    }
}
