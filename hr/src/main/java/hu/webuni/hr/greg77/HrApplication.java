package hu.webuni.hr.greg77;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	List<Employee> employees = new ArrayList<>();

	{
		employees.add(new Employee(1, "Anna Smith", "chief", 1000, LocalDateTime.now().minusMonths(130)));
		employees.add(new Employee(2, "Bob Tailor", "assistant", 1000, LocalDateTime.now().minusMonths(119)));
		employees.add(new Employee(3, "Charles Adams", "section head", 1000, LocalDateTime.now().minusMonths(74)));
		employees.add(new Employee(4, "Diane Kerrigan", "adjutant", 1000, LocalDateTime.now().minusMonths(55)));
		employees.add(new Employee(5, "Eric Tesla", "technician", 1000, LocalDateTime.now().minusMonths(28)));
	}

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (Employee e : employees) {
			System.out.println(e.toString() + " ---> raised salary: " + salaryService.getNewSalary(e));
		}
	}

}
