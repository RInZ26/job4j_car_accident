package ru.job4j.accident.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.dao.jdbc.dataextract.mapper.AccidentMapper;
import ru.job4j.accident.dao.jdbc.dataextract.rsextractor.AccidentExtractor;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@PropertySource("classpath:jdbc/queryParams.properties")
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    private final AccidentExtractor accidentExtractor;

    private final AccidentMapper accidentMapper;

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

    private String selectAllQuery;

    private String selectByIdQuery;

    @Autowired
    public AccidentJdbcTemplate(JdbcTemplate jdbc, AccidentExtractor accidentExtractor,
                                AccidentMapper accidentMapper) {
        this.jdbc = jdbc;
        this.accidentExtractor = accidentExtractor;
        this.accidentMapper = accidentMapper;
    }

    @PostConstruct
    private void init() {
        selectAllQuery = String.format(
                "SELECT a.id %s, a.name %s, a.text %s, a.address %s, t.id %s,t.name %s, r.id "
                        + "%s, r.name %s FROM accident AS a LEFT "
                        + "JOIN accidenttype AS t ON a.type_id = t"
                        + ".id LEFT JOIN accident_rule AS ar ON a.id = ar.accident_id"
                        + " LEFT JOIN rule AS r ON ar.rules_id = r.id ORDER BY a.id", accidentId,
                accidentName, accidentText, accidentAddress, accidentTypeId, accidentTypeName,
                ruleId, ruleName);

        selectByIdQuery = String.format(
                "SELECT a.id %s, a.name %s, a.text %s, a.address %s, t.id %s, t.name %s, r.id "
                        + "%s, r.name %s FROM accident AS a LEFT "
                        + "JOIN accidenttype AS t ON a.type_id = t"
                        + ".id LEFT JOIN accident_rule AS ar ON a.id = ar.accident_id"
                        + " LEFT JOIN rule AS r ON ar.rules_id = r.id WHERE a.id = ?", accidentId,
                accidentName, accidentText, accidentAddress, accidentTypeId, accidentTypeName,
                ruleId, ruleName);
    }

    @Transactional
    public void save(Accident accident) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO accident (name, address, text, type_id) VALUES (?,?,?,?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getAddress());
            ps.setString(3, accident.getText());
            ps.setInt(4, accident.getType()
                                 .getId());
            return ps;
        }, keyHolder);

        Set<Rule> ruleSet = Optional.of(accident.getRules())
                                    .orElse(Collections.emptySet());

        for (Rule rule : ruleSet) {
            jdbc.update("INSERT INTO accident_rule (accident_id, rules_id) VALUES (?,?)",
                    keyHolder.getKey(), rule.getId());
        }
    }

    @Transactional
    public void update(Accident accident, int id) {
        jdbc.update("UPDATE accident SET name = ?, address = ?, text = ?, type_id = ? WHERE id = ?",
                accident.getName(), accident.getAddress(), accident.getText(), accident.getType()
                                                                                       .getId(),
                id);

        jdbc.update("DELETE FROM accident_rule AS ar WHERE ar.accident_id = ?", id);
        Set<Rule> ruleSet = Optional.of(accident.getRules())
                                    .orElse(Collections.emptySet());
        for (Rule rule : ruleSet) {
            jdbc.update("INSERT INTO accident_rule (accident_id, rules_id) VALUES (?,?)", id,
                    rule.getId());
        }
    }

    public Accident findById(int id) {
        return jdbc.queryForObject(selectByIdQuery, accidentMapper, id);
    }

    public List<Accident> findAll() {
        return jdbc.query(selectAllQuery, accidentExtractor);
    }
}