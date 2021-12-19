package ru.job4j.accident.dao.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Query("SELECT DISTINCT a FROM Accident a LEFT JOIN FETCH a.rules LEFT JOIN FETCH a.type")
    List<Accident> findAll(Sort sort);

    @Query("SELECT a FROM Accident a LEFT JOIN FETCH a.rules LEFT JOIN FETCH a.type WHERE a.id=?1")
    Accident findById(int id);
}