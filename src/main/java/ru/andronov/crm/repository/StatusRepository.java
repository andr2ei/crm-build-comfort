package ru.andronov.crm.repository;

import org.springframework.stereotype.Repository;
import ru.andronov.crm.domain.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository

public class StatusRepository implements IStatusRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Status getById(int id) {
        var ql = "SELECT s FROM Status s WHERE s.id = :id";
        TypedQuery<Status> query = em.createQuery(ql, Status.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Status save(Status status) {
        em.persist(status);
        return status;
    }

    @Override
    public Status update(Status status) {
        return em.merge(status);
    }

    @Override
    public List<Status> findAll() {
        var ql = "SELECT s FROM Status s";
        return em.createQuery(ql, Status.class).getResultList();
    }
}
