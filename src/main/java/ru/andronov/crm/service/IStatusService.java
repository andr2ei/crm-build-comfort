package ru.andronov.crm.service;

import ru.andronov.crm.domain.Status;

import java.util.List;

public interface IStatusService {

    Status addStatus(Status status);

    Status update(Status status);

    List<Status> getAll();

}
