package ru.andronov.crm.repository;

import ru.andronov.crm.domain.Status;

import java.util.List;

public interface IStatusRepository {
    Status save(Status status);
    void update(Status status);
    List<Status> findAll();
}
