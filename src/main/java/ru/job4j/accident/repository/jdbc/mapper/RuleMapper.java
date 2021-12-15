package ru.job4j.accident.repository.jdbc.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@PropertySource("classpath:jdbc/queryParams.properties")
public class RuleMapper implements RowMapper<Rule> {
    @Value("${query.ruleId}")
    private String ruleId;

    @Value("${query.ruleName}")
    private String ruleName;

    @Override
    public Rule mapRow(ResultSet rs, int i)
            throws SQLException {

        return Rule.of(rs.getInt(ruleId), rs.getString(ruleName));
    }
}