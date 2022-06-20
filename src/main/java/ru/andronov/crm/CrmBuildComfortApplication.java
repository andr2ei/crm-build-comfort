package ru.andronov.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.andronov.crm.domain.Status;
import ru.andronov.crm.repository.IStatusRepository;

import java.util.List;

@SpringBootApplication
public class CrmBuildComfortApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(CrmBuildComfortApplication.class, args);

//		IStatusRepository statusRepository = ctx.getBean(IStatusRepository.class);
//		var status = new Status("New");
//		statusRepository.save(status);
//		System.out.println("status created = " + status);
//
//		status.setName("New updated");
//		statusRepository.update(status);
//
//		List<Status> statuses = statusRepository.findAll();
//		System.out.println("all statuses = " + statuses);

	}

}
