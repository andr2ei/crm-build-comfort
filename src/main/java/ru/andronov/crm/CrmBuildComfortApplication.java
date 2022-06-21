package ru.andronov.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.repository.ILeadRepository;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class CrmBuildComfortApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(CrmBuildComfortApplication.class, args);

		var leadRepo = ctx.getBean(ILeadRepository.class);
		var status = new Status(11, "New updated");
		var newLead = Lead.builder()
				.status(status)
				.firstName("first name 1")
				.lastName("last name 1")
				.phone("+79211113344")
				.address("addres 1")
				.creationDate(Date.valueOf(LocalDate.now()))
				.build();
		var createdLead = leadRepo.save(newLead);
		System.out.println("createdLead = " + createdLead);
	}

}
