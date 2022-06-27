package ru.andronov.crm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.andronov.crm.domain.Lead;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeadRepository implements ILeadRepository {

    private final EntityManager em;

    @Override
    public Lead save(Lead lead) {
        em.persist(lead);
        return lead;
    }

    @Override
    public Lead update(Lead lead) {
        return em.merge(lead);
    }

    @Override
    public List<Lead> findAll() {
        var findAllQuery = "SELECT l FROM Lead l";
        return em.createQuery(findAllQuery, Lead.class).getResultList();
    }

    @Override
    public List<Lead> findAllByStatusId(int statusId) {
        var findAllQuery = "SELECT l FROM Lead l WHERE l.status.id = :statusId";
        TypedQuery<Lead> query = em.createQuery(findAllQuery, Lead.class);
        return query.setParameter("statusId", statusId).getResultList();
    }
}
