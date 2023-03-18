package hu.webuni.hr.greg77;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(salaryService
				.getNewSalary(new Employee(1, "Anna Smith", "chief", 1000, LocalDateTime.now().minusMonths(130))));
		System.out.println(salaryService
				.getNewSalary(new Employee(2, "Bob Tailor", "assistant", 1000, LocalDateTime.now().minusMonths(119))));
		System.out.println(salaryService.getNewSalary(
				new Employee(3, "Charles Adams", "section head", 1000, LocalDateTime.now().minusMonths(74))));
		System.out.println(salaryService.getNewSalary(
				new Employee(4, "Diane Kerrigan", "adjutant", 1000, LocalDateTime.now().minusMonths(55))));
		System.out.println(salaryService
				.getNewSalary(new Employee(5, "Eric Tesla", "technician", 1000, LocalDateTime.now().minusMonths(28))));
	}

}
