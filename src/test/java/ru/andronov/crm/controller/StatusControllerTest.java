package ru.andronov.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.service.StatusService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatusController.class)
class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @Test
    void getAllStatuses() throws Exception {
        Mockito.when(statusService.getAll())
                .thenReturn(List.of(new Status(1, "Completed"), new Status(2, "New")));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/v1/status/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Completed\"},{\"id\":2,\"name\":\"New\"}]"));
    }

    @Test
    void addNewStatus() throws Exception {
        var status = new Status(1, "Loading");
        Mockito.when(statusService.addStatus(status))
                .thenReturn(status);
        var objMapper = new ObjectMapper();
        var strContent = objMapper.writeValueAsString(status);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/api/v1/status/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(strContent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Loading\"}"));
    }

    @Test
    void updateStatus() throws Exception {
        var status = new Status(1, "Loading");
        Mockito.when(statusService.update(status))
                .thenReturn(status);
        var objMapper = new ObjectMapper();
        var strContent = objMapper.writeValueAsString(status);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("http://localhost:8080/api/v1/status/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(strContent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Loading\"}"));
    }
}