package ru.andronov.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.dto.IncomePerMonthDTO;
import ru.andronov.crm.dto.Tuple;
import ru.andronov.crm.repository.ILeadRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Lead> getAll(int pageNumber, int pageSize) {
        return leadRepository.findAll(pageNumber, pageSize);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true)
    @Override
    public List<Lead> getByStatusId(int statusId) {
        return leadRepository.findAllByStatusId(statusId);
    }

    @Override
    public List<IncomePerMonthDTO> getAllIncomePerMonth() {
        Map<Tuple<Integer>, List<Lead>> collectedMap = leadRepository.findAllWithLastStatus()
                .stream()
                .collect(Collectors.groupingBy(lead -> new Tuple<>(lead.creationYear(), lead.creationMonth())));
        return collectedMap.entrySet().stream()
                .map(entry -> {
                    var year = entry.getKey().getFirst();
                    var month = entry.getKey().getSecond();
                    var totalTradePrice = entry.getValue()
                            .stream()
                            .map(Lead::getTradePrice)
                            .reduce(0.0, Double::sum);
                    var totalCost = entry.getValue()
                            .stream().map(Lead::totalCostWithDiscount)
                            .reduce(0.0, Double::sum);
                    var totalIncome = totalCost - totalTradePrice;
                    return new IncomePerMonthDTO(year, month, totalTradePrice, totalCost, totalIncome);
                })
                .sorted(Comparator
                        .comparingInt(IncomePerMonthDTO::getYear).reversed()
                        .thenComparingInt(IncomePerMonthDTO::getMonth).reversed())
                .collect(Collectors.toList());
    }
}
