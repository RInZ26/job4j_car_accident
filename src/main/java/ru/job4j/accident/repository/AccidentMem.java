package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    /**
     * Делаем вид, что у нас нет ORM и id мы проставляем сами
     */
    private static final AtomicInteger IDENTITY_COUNTER = new AtomicInteger(0);

    private static final Map<Integer, Accident> ACCIDENTS =
            new HashMap<>(Map.of(IDENTITY_COUNTER.addAndGet(1), new Accident(IDENTITY_COUNTER.get(),
                            "Beautiful", "alina is an " + "accident", "UnicornLand"),
                    IDENTITY_COUNTER.addAndGet(1), new Accident(IDENTITY_COUNTER.get(), "Awful",
                            "ksusha", "sadLand")));

    public void addAccident(Accident accident) {
        int key = IDENTITY_COUNTER.addAndGet(1);
        accident.setId(key);
        changeAccident(key, accident);
    }

    public void changeAccident(Integer id, Accident accident) {
        ACCIDENTS.put(id, accident);
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(ACCIDENTS.values());
    }
}
