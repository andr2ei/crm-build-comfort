package ru.andronov.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.repository.ILeadRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadService implements ILeadService {

    private final ILeadRepository leadRepository;

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRED)
    @Override
    public Lead create(Lead lead) {
        return leadRepository.save(lead);
    }

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRED)
    @Override
    public Lead update(Lead lead) {
        return leadRepository.update(lead);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true)
    @Override
    public List<Lead> getAll() {
        return leadRepository.findAll();
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true)
    @Override
    public List<Lead> getByStatusId(int statusId) {
        return leadRepository.findAllByStatusId(statusId);
    }
}
