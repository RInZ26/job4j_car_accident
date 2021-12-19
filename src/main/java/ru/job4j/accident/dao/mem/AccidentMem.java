package ru.job4j.accident.dao.mem;

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

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public void save(Accident accident) {
        int key = identityCounter.addAndGet(1);
        accident.setId(key);
        change(key, accident);
    }

    public void change(Integer id, Accident accident) {
        accidents.put(id, accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
