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

    public List<Accident> getAllAccidents() {
        return accidentMem.getAllAccidents();
    }

    public void saveAccident(Accident accident) {
        if (0 == accident.getId()) {
            accidentMem.addAccident(accident);
        } else {
            changeAccident(accident.getId(), accident);
        }
    }

    private void changeAccident(Integer id, Accident accident) {
        accidentMem.changeAccident(id, accident);
    }
}
