package ru.andronov.crm.repository;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.domain.Status;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(LeadRepository.class)
class LeadRepositoryTest {

    @Autowired
    private ILeadRepository leadRepository;

    @Test
    void save() {
        var leadToSave = Lead.builder()
                .firstName("firs name 4").lastName("last name 4")
                .address("address 4")
                .creationDate(Date.valueOf(LocalDate.of(2022, 6, 27)))
                .discount(4)
                .email("email 4")
                .storage("storage 4")
                .phone("phone 4")
                .status(new Status(1, ""))
                .build();
        Lead actualLead = leadRepository.save(leadToSave);
        var expected = Lead.builder()
                .id(4)
                .firstName("firs name 4").lastName("last name 4")
                .address("address 4")
                .creationDate(Date.valueOf(LocalDate.of(2022, 6, 27)))
                .discount(4)
                .email("email 4")
                .storage("storage 4")
                .phone("phone 4")
                .status(new Status(1, ""))
                .build();
        Assertions.assertEquals(expected, actualLead);
    }

    @Test
    void update() {
        var lead = Lead.builder()
                .id(3)
                .firstName("firs name 4").lastName("last name 4")
                .address("address 4")
                .creationDate(Date.valueOf(LocalDate.of(2022, 6, 27)))
                .discount(4)
                .email("email 4")
                .storage("storage 4")
                .phone("phone 4")
                .status(new Status(1, "status name 1"))
                .build();
        var actualLead = leadRepository.update(lead);
        Assertions.assertEquals(lead, actualLead);

    }

    @Test
    void findAll() {
        var all = leadRepository.findAll();
        Assertions.assertEquals(3, all.size());
    }

    @Test
    void findAllByStatusId() {
        var all = leadRepository.findAllByStatusId(1);
        Assertions.assertEquals(2, all.size());
    }
}