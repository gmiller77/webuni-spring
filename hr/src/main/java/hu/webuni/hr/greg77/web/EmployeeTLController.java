package hu.webuni.hr.greg77.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.greg77.dto.EmployeeDto;

@Controller
public class EmployeeTLController {

	private List<EmployeeDto> allEmployees = new ArrayList<>();

	{
		allEmployees.add(new EmployeeDto(1, "Anna Smith", "chief", 1000, LocalDateTime.now().minusMonths(130)));
		allEmployees.add(new EmployeeDto(2, "Bob Tailor", "assistant", 1000, LocalDateTime.now().minusMonths(119)));
		allEmployees
				.add(new EmployeeDto(3, "Charles Adams", "section head", 1000, LocalDateTime.now().minusMonths(74)));
		allEmployees.add(new EmployeeDto(4, "Diane Kerrigan", "adjutant", 1000, LocalDateTime.now().minusMonths(55)));
		allEmployees.add(new EmployeeDto(5, "Eric Tesla", "technician", 1000, LocalDateTime.now().minusMonths(28)));
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", allEmployees);
		model.put("newEmployee", new EmployeeDto());
		return "employees";
	}
	
	@GetMapping("/id")
	public String editEmployees(Map<String, Object> model) {
		model.put("employees", allEmployees);
		model.put("newEmployee", new EmployeeDto());
		return "employeeByID";
	}

	@PostMapping("/employees")
	public String addEmployee(EmployeeDto employee) {
		allEmployees.add(employee);
		return "redirect:employees";
	}
}
