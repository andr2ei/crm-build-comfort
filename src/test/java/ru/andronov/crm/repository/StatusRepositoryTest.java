package ru.andronov.crm.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.andronov.crm.domain.Status;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(StatusRepository.class)
class StatusRepositoryTest {

    @Autowired
    private IStatusRepository statusRepository;

    @Test
    void shouldReturnStatusById() {
        Status actualStatus = statusRepository.getById(1);
        Status expectedStatus = new Status(1, "status name 1");
        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void save() {
        Status status = new Status("status name 4");
        Status actualStatus = statusRepository.save(status);
        Status expectedStatus = new Status(4, "status name 4");
        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void update() {
        Status status = new Status(3, "status name 3 updated");
        statusRepository.update(status);
        Status actualStatus = statusRepository.getById(3);
        Assertions.assertEquals(status, actualStatus);
    }

    @Test
    void findAll() {
        List<Status> all = statusRepository.findAll();
        Assertions.assertEquals(3, all.size());
    }
}