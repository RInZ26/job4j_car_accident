package ru.job4j.accident.repository.jdbc.dataextract.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.jdbc.dataextract.AccidentExtractHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccidentMapper implements RowMapper<Accident> {
    private final AccidentExtractHelper accidentExtractHelper;
    private final RuleMapper ruleMapper;

    @Autowired
    public AccidentMapper(AccidentExtractHelper accidentExtractHelper, RuleMapper ruleMapper) {
        this.accidentExtractHelper = accidentExtractHelper;
        this.ruleMapper = ruleMapper;
    }

    @Override
    public Accident mapRow(ResultSet rs, int i)
            throws SQLException {

        Accident accident = null;

        do {
            if (null == accident) {
                accident = accidentExtractHelper.extract(rs);
            }
            accident.getRules()
                    .add(ruleMapper.mapRow(rs, 0));
        } while (rs.next());

        return accident;
    }
}
