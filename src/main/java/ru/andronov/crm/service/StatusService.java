package ru.andronov.crm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.repository.StatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED)
@Slf4j
public class StatusService implements IStatusService {
    private final StatusRepository statusRepository;

    @Override
    public Status addStatus(Status status) {
        log.info("Adding new status: {}", status.getName());
        return statusRepository.save(status);
    }

    @Override
    public Status update(Status status) {
        log.info("Updating status with id {} and name {}", status.getId(), status.getName());
        return statusRepository.update(status);
    }

    @Override
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true)
    public List<Status> getAll() {
        List<Status> all = statusRepository.findAll();
        log.info("Retrieved {} number of statuses", all.size());
        return all;
    }
}
