package ru.andronov.crm.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.repository.IStatusRepository;
import ru.andronov.crm.repository.StatusRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StatusServiceTest {

    @Mock
    private IStatusRepository statusRepository;

    private IStatusService statusService;

    @BeforeEach
    void init() {
        statusService = new StatusService(statusRepository);
    }

    @Test
    void addStatus() {
        var statusToAdd = new Status(0, "status name");
        var result = new Status(1, "status name");
        Mockito.when(statusRepository.save(statusToAdd)).thenReturn(result);
        Status addedStatus = statusService.addStatus(statusToAdd);
        Assertions.assertEquals(result, addedStatus);
    }

    @Test
    void update() {
        var statusToUpdate = new Status(1, "status name updated");
        Mockito.when(statusRepository.update(statusToUpdate)).thenReturn(statusToUpdate);
        Status updatedStatus = statusService.update(statusToUpdate);
        Assertions.assertEquals(statusToUpdate, updatedStatus);
    }

    @Test
    void getAll() {
        var Status = new Status(1, "status name updated");
        Mockito.when(statusRepository.findAll()).thenReturn(List.of(Status));
        List<Status> statuses = statusService.getAll();
        Assertions.assertEquals(1, statuses.size());
    }
}