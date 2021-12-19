package ru.job4j.accident.dao.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    @Autowired
    public AccidentHibernate(SessionFactory sf) {
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

    public void save(Accident accident) {
        performTx(s -> s.save(accident));
    }

    public void update(Accident accident) {
        performTx(s -> s.merge(accident));
    }

    public List<Accident> findAll() {
        return performTx(s -> s.createQuery(
                "select distinct a from Accident a left join fetch a.type left join fetch"
                        + " a.rules order by a.id").list());
    }

    public Accident findById(int id) {
        return (Accident) performTx(s -> s.createQuery(
                "select a from Accident a left join fetch a.type left join fetch"
                        + " a.rules where a.id = :id ").setParameter("id", id).getSingleResult());
    }
}