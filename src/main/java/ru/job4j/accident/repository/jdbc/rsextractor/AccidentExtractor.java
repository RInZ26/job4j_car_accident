package ru.job4j.accident.repository.jdbc.rsextractor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@PropertySource("classpath:jdbc/queryParams.properties")
public class AccidentExtractor implements ResultSetExtractor<List<Accident>> {
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

    @Value("${query.ruleId}")
    private String ruleId;

    @Value("${query.ruleName}")
    private String ruleName;

    @Override
    public List<Accident> extractData(ResultSet rs)
            throws SQLException, DataAccessException {
        Map<Integer, Accident> map = new LinkedHashMap<>();

        while (rs.next()) {

            int accId = rs.getInt(accidentId);

            map.putIfAbsent(accId, Accident.of(accId, rs.getString(accidentName),
                    rs.getString(accidentText), rs.getString(accidentAddress),
                    AccidentType.of(rs.getInt(accidentTypeId),
                            rs.getString(accidentTypeName))));

            map.get(accId)
               .addRule(Rule.of(rs.getInt(ruleId), rs.getString(ruleName)));
        }

        return new ArrayList<>(map.values());
    }
}
