package ru.andronov.crm.repository;

import ru.andronov.crm.domain.Lead;

import java.util.List;

public interface ILeadRepository {

    Lead save(Lead lead);
    Lead update(Lead lead);
    List<Lead> findAll(int pageNumber, int pageSize);
    List<Lead> findAllByStatusId(int statusId);
    List<Lead> findAllWithLastStatus();
}
