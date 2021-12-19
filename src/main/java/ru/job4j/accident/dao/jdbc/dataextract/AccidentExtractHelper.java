package ru.job4j.accident.dao.jdbc.dataextract;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@PropertySource("classpath:jdbc/queryParams.properties")
public class AccidentExtractHelper {
    @Value("${query.accidentId}")
    private String accidentId;

    @Value("${query.accidentName}")
    private String accidentName;

    @Value("${query.accidentText}")
    private String accidentText;

    @Value("${query.accidentAddress}")
    private String accidentAddress;

    @Value("${query.accidentTypeId}")
    private String accidentTypeId;

    @Value("${query.accidentTypeName}")
    private String accidentTypeName;

    public Accident extract(ResultSet rs)
            throws SQLException, DataAccessException {

        return Accident.of(rs.getInt(accidentId), rs.getString(accidentName),
                rs.getString(accidentText), rs.getString(accidentAddress),
                AccidentType.of(rs.getInt(accidentTypeId), rs.getString(accidentTypeName)));
    }
}
