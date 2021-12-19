package ru.job4j.accident.repository.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentTypeHibernate {
    private final SessionFactory sf;

    @Autowired
    public AccidentTypeHibernate(SessionFactory sf) {
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

    public void save(AccidentType accidentType) {
        performTx(s -> s.save(accidentType));
    }

    public void update(AccidentType accidentType) {
        performTx(s -> s.merge(accidentType));
    }

    public List<AccidentType> findAll() {
        return performTx(s -> s.createQuery("from AccidentType ").list());
    }

    public AccidentType findById(int id) {
        return (AccidentType) performTx(
                s -> s.createQuery("select a from AccidentType a where a.id = :id ")
                      .setParameter("id", id).getSingleResult());
    }
}
