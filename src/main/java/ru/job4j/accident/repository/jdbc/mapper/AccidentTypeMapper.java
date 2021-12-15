package ru.job4j.accident.repository.jdbc.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@PropertySource("classpath:jdbc/queryParams.properties")
public class AccidentTypeMapper implements RowMapper<AccidentType> {
    @Value("${query.accidentTypeId}")
    private String accidentTypeId;

    @Value("${query.accidentTypeName}")
    private String accidentTypeName;

    @Override
    public AccidentType mapRow(ResultSet resultSet, int i)
            throws SQLException {

        return AccidentType.of(resultSet.getInt(accidentTypeId),
                resultSet.getString(accidentTypeName));
    }
}
