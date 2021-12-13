package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.Accident;

import java.util.List;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("INSERT INTO accident (name) VALUES (?)", accident.getName());
        return accident;
    }

    public void update(Accident accident, int id) {
        jdbc.update("UPDATE accident SET name = ? WHERE id = ?", accident.getName(), id);
    }

    public Accident findById(int id) {
        return jdbc.query("SELECT id, name FROM accident AS a WHERE a.id = ? FETCH FIRST 1 ROWS "
                + "ONLY", rs -> {
            rs.next();
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            return accident;
        }, id);
    }

    public List<Accident> findAll() {
        return jdbc.query("SELECT id, name FROM accident ORDER BY id", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            return accident;
        });
    }
}