package ru.andronov.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.service.IStatusService;

import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/statuses")
@RequiredArgsConstructor
public class StatusController {
    private final IStatusService statusService;

    @GetMapping
    public List<Status> getAllStatuses() {
        return statusService.getAll();
    }

    @PostMapping
    public Status addNewStatus(@RequestBody Status status) {
        return statusService.addStatus(status);
    }

    @PutMapping
    public void updateStatus(@RequestBody Status status) {
        statusService.update(status);
    }

}
