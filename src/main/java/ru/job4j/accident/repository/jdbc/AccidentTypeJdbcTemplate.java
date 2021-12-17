package ru.job4j.accident.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.jdbc.dataextract.mapper.AccidentTypeMapper;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository(value = "accidentTypeJdbc")
@PropertySource("classpath:jdbc/queryParams.properties")
public class AccidentTypeJdbcTemplate {

    private final AccidentTypeMapper accidentTypeMapper;

    private final JdbcTemplate jdbc;

    @Value("${query.accidentTypeId}")
    private String typeId;

    @Value("${query.accidentTypeName}")
    private String typeName;

    private String selectAllQuery;

    @Autowired
    public AccidentTypeJdbcTemplate(AccidentTypeMapper accidentTypeMapper, JdbcTemplate jdbc) {
        this.accidentTypeMapper = accidentTypeMapper;
        this.jdbc = jdbc;
    }

    @PostConstruct
    private void init() {
        selectAllQuery = String.format("SELECT id %s, name %s FROM accidenttype ORDER BY id",
                typeId, typeName);
    }

    public List<AccidentType> findAll() {
        return jdbc.query(selectAllQuery, accidentTypeMapper);
    }
}
