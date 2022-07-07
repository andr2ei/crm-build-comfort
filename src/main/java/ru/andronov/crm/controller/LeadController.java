package ru.andronov.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.dto.IncomePerMonthDTO;
import ru.andronov.crm.service.ILeadService;

import java.util.List;

@RestController
@RequestMapping("api/v1/lead")
@RequiredArgsConstructor
@Slf4j
public class LeadController {

    private final ILeadService leadService;

    @GetMapping(path = "all/income")
    public List<IncomePerMonthDTO> getAllIncomePerMonth() {
        return leadService.getAllIncomePerMonth();
    }

    @GetMapping(path = "all")
    public List<Lead> getAll() {
        return leadService.getAll(1, 100000);
    }
//    @GetMapping(path = "all")
//    public List<Lead> getAll(@RequestParam("pageNumber") int pageNumber,
//                             @RequestParam("pageSize") int pageSize) {
//        return leadService.getAll(pageNumber, pageSize);
//    }

    @GetMapping(path = "all/status/{statusId}")
    public List<Lead> getAllByStatusId(@PathVariable(name = "statusId") int statusId) {
        return leadService.getByStatusId(statusId);
    }

    @PostMapping(path = "create")
    public Lead create(@RequestBody Lead lead) {
        log.debug("Creating lead " + lead );
        return leadService.create(lead);
    }

    @PutMapping(path = "edit")
    public Lead update(@RequestBody Lead lead) {
        return leadService.update(lead);
    }
}
