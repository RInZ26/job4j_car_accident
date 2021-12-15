package ru.job4j.accident.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.jdbc.mapper.RuleMapper;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class RuleJdbcTemplate {

    private final JdbcTemplate jdbc;

    private final RuleMapper ruleMapper;

    @Value("${query.ruleId}")
    private String ruleId;

    @Value("${query.ruleName}")
    private String ruleName;

    private String selectAllQuery;

    @Autowired
    public RuleJdbcTemplate(JdbcTemplate jdbc, RuleMapper ruleMapper) {
        this.jdbc = jdbc;
        this.ruleMapper = ruleMapper;
    }

    @PostConstruct
    private void init() {
        selectAllQuery = String.format("SELECT id %s, name %s FROM rule ORDER BY id", ruleId,
                ruleName);
    }

    public List<Rule> findAll() {
        return jdbc.query(selectAllQuery, ruleMapper);
    }
}
