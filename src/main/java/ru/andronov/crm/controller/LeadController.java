package ru.andronov.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.service.ILeadService;

import java.util.List;

@RestController
@RequestMapping("api/v1/lead")
@RequiredArgsConstructor
public class LeadController {

    private final ILeadService leadService;

    @GetMapping(path = "all")
    public List<Lead> getAll() {
        return leadService.getAll();
    }

    @GetMapping(path = "all/status/{statusId}")
    public List<Lead> getAllByStatusId(@PathVariable(name = "statusId") int statusId) {
        return leadService.getByStatusId(statusId);
    }

    @PostMapping(path = "create")
    public Lead create(@RequestBody Lead lead) {
        return leadService.create(lead);
    }

    @PutMapping(path = "edit")
    public void update(@RequestBody Lead lead) {
        leadService.update(lead);
    }
}