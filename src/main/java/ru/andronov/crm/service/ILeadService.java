package ru.andronov.crm.service;

import ru.andronov.crm.domain.Lead;

import java.util.List;

public interface ILeadService {

    Lead create(Lead lead);

    void update(Lead lead);

    List<Lead> getAll();

    List<Lead> getByStatusId(int statusId);
}
