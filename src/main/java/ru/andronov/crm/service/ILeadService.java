package ru.andronov.crm.service;

import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.dto.IncomePerMonthDTO;

import java.util.List;

public interface ILeadService {

    Lead create(Lead lead);

    Lead update(Lead lead);

    List<Lead> getAll(int pageNumber, int pageSize);

    List<Lead> getByStatusId(int statusId);

    List<IncomePerMonthDTO> getAllIncomePerMonth();
}
