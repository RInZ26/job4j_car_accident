package ru.job4j.accident.dao.jdbc.dataextract.rsextractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.job4j.accident.dao.jdbc.dataextract.AccidentExtractHelper;
import ru.job4j.accident.dao.jdbc.dataextract.mapper.RuleMapper;
import ru.job4j.accident.model.Accident;

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

    private final AccidentExtractHelper accidentExtractHelper;
    private final RuleMapper ruleMapper;

    @Autowired
    public AccidentExtractor(AccidentExtractHelper accidentExtractHelper, RuleMapper ruleMapper) {
        this.accidentExtractHelper = accidentExtractHelper;
        this.ruleMapper = ruleMapper;
    }

    @Override
    public List<Accident> extractData(ResultSet rs)
            throws SQLException, DataAccessException {
        Map<Integer, Accident> accidentMap = new LinkedHashMap<>();

        while (rs.next()) {

            int accId = rs.getInt(accidentId);

            accidentMap.putIfAbsent(accId, accidentExtractHelper.extract(rs));

            accidentMap.get(accId)
                       .addRule(ruleMapper.mapRow(rs, 0));
        }

        return new ArrayList<>(accidentMap.values());
    }
}
