package ru.andronov.crm.repository;

import ru.andronov.crm.domain.Lead;

import java.util.List;

public interface ILeadRepository {

    Lead save(Lead lead);
    void update(Lead lead);
    List<Lead> findAll();
    List<Lead> findAllByStatusId(int statusId);
}
