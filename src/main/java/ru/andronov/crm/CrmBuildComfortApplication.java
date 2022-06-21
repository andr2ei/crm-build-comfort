package ru.andronov.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.andronov.crm.domain.Lead;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.repository.ILeadRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CrmBuildComfortApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(CrmBuildComfortApplication.class, args);

		var leadRepo = ctx.getBean(ILeadRepository.class);

		var status11 = new Status(11, "New 1 updated");
		var newLead = Lead.builder()
				.status(status11)
				.firstName("first name 1")
				.lastName("last name 1")
				.phone("+79211113344")
				.address("addres 1")
				.creationDate(Date.valueOf(LocalDate.now()))
				.build();
		var createdLead = leadRepo.save(newLead);
		System.out.println("createdLead = " + createdLead);

		var status12 = new Status(12, "New 1 updated");
		createdLead.setStatus(status12);
		leadRepo.update(createdLead);

		List<Lead> leads = leadRepo.findAll();
		System.out.println("leads = " + leads);

		System.out.println("leadRepo.findAllByStatusId(12) = " + leadRepo.findAllByStatusId(12));
	}

}
