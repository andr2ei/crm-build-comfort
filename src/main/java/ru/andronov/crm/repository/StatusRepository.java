package ru.andronov.crm.repository;

import org.springframework.stereotype.Repository;
import ru.andronov.crm.domain.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StatusRepository implements IStatusRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Status save(Status status) {
        em.persist(status);
        return status;
    }

    @Override
    public void update(Status status) {
        em.merge(status);
    }

    @Override
    public List<Status> findAll() {
        var ql = "SELECT s FROM Status s";
        return em.createQuery(ql, Status.class).getResultList();
    }
}
