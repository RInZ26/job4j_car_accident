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
    private final AtomicInteger identityCounter = new AtomicInteger(0);

    private final Map<Integer, Accident> accidents = new HashMap<>(
            Map.of(identityCounter.addAndGet(1),
                    new Accident(identityCounter.get(), "Beautiful", "alina is an " + "accident",
                            "UnicornLand"), identityCounter.addAndGet(1),
                    new Accident(identityCounter.get(), "Awful", "ksusha", "sadLand")));

    public void addAccident(Accident accident) {
        int key = identityCounter.addAndGet(1);
        accident.setId(key);
        changeAccident(key, accident);
    }

    public void changeAccident(Integer id, Accident accident) {
        accidents.put(id, accident);
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidents.values());
    }
}
