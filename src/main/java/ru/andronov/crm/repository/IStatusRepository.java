package ru.andronov.crm.repository;

import ru.andronov.crm.domain.Status;

import java.util.List;

public interface IStatusRepository {

    Status getById(int id);
    Status save(Status status);
    Status update(Status status);
    List<Status> findAll();
}
