package ru.job4j.accident.dao.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

@Repository
public class RuleHibernate {

    private final SessionFactory sf;

    @Autowired
    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T performTx(Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void save(Rule rule) {
        performTx(s -> s.save(rule));
    }

    public void update(Rule rule) {
        performTx(s -> s.merge(rule));
    }

    public List<Rule> findAll() {
        return performTx(s -> s.createQuery("from Rule ").list());
    }

    public Rule findById(int id) {
        return (Rule) performTx(
                s -> s.createQuery("select r from Rule r where r.id = :id ").setParameter("id", id)
                      .getSingleResult());
    }
}
