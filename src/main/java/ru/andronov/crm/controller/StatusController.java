package ru.andronov.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.service.IStatusService;

import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/status")
@RequiredArgsConstructor
public class StatusController {
    private final IStatusService statusService;

    @GetMapping(path = "all")
    public List<Status> getAllStatuses() {
        return statusService.getAll();
    }

    @PostMapping(path = "create")
    public Status addNewStatus(@RequestBody Status status) {
        return statusService.addStatus(status);
    }

    @PutMapping(path = "edit")
    public Status updateStatus(@RequestBody Status status) {
        return statusService.update(status);
    }

}
