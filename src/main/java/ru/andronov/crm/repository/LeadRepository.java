package ru.andronov.crm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.andronov.crm.domain.Lead;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class LeadRepository implements ILeadRepository {

    private final EntityManager em;

    @Override
    public Lead save(Lead lead) {
        em.persist(lead);
        return lead;
    }

    @Override
    public void update(Lead lead) {
        em.merge(lead);
    }

    @Override
    public List<Lead> findAll() {
        var findAllQuery = "SELECT l, s FROM Lead l JOIN FETCH Status s";
        return em.createQuery(findAllQuery, Lead.class).getResultList();
    }

    @Override
    public List<Lead> findAllByStatusId(int statusId) {
        var findAllQuery = "SELECT l, s FROM Lead l JOIN FETCH Status s WHERE s.statusId = " + statusId;
        return em.createQuery(findAllQuery, Lead.class).getResultList();
    }
}
