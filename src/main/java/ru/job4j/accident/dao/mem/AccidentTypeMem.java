package ru.job4j.accident.dao.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {
    /**
     * Делаем вид, что у нас нет ORM и id мы проставляем сами
     */
    private final AtomicInteger identityCounter = new AtomicInteger(0);

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>(
            Map.of(identityCounter.addAndGet(1), AccidentType.of(1, "Две машины"),
                    identityCounter.addAndGet(1), AccidentType.of(2, "Машина и человек"),
                    identityCounter.addAndGet(1), AccidentType.of(3, "Машина и велосипед")));

    public void save(AccidentType accidentType) {
        change(identityCounter.addAndGet(1), accidentType);
    }

    public void change(int key, AccidentType accidentType) {
        accidentTypes.put(key, accidentType);
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(accidentTypes.values());
    }

    public AccidentType findById(int id) {
        return accidentTypes.get(id);
    }
}
