package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = Map.of(0,
            new Accident("Beautiful", "alina is an accident", "UnicornLand"), 1,
            new Accident("Awful", "ksusha", "sadLand"));

    public void addAccident(int id, Accident accident) {
        accidents.put(id, accident);
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidents.values());
    }
}
