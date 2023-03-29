package hu.webuni.hr.greg77.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.greg77.dto.EmployeeDto;

@Controller
public class EmployeeTLController {

	private List<EmployeeDto> thymeEmployees = new ArrayList<>();

	{
		thymeEmployees.add(new EmployeeDto(1, "Anna Smith", "chief", 1000, LocalDateTime.now().minusMonths(130)));
		thymeEmployees.add(new EmployeeDto(2, "Bob Tailor", "assistant", 1000, LocalDateTime.now().minusMonths(119)));
		thymeEmployees
				.add(new EmployeeDto(3, "Charles Adams", "section head", 1000, LocalDateTime.now().minusMonths(74)));
		thymeEmployees.add(new EmployeeDto(4, "Diane Kerrigan", "adjutant", 1000, LocalDateTime.now().minusMonths(55)));
		thymeEmployees.add(new EmployeeDto(5, "Eric Tesla", "technician", 1000, LocalDateTime.now().minusMonths(28)));
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", thymeEmployees);
		model.put("newEmployee", new EmployeeDto());
		return "employees";
	}

	@PostMapping("/employees")
	public String addEmployee(EmployeeDto employee) {
		thymeEmployees.add(employee);
		return "redirect:employees";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable long id) {
		thymeEmployees.removeIf(e -> e.getId() == id);
		return "redirect:/employees";
	}

	@GetMapping("/employees/{id}")
	public String editEmployee(@PathVariable long id, Map<String, Object> model) {
		model.put("employeeToEdit", thymeEmployees.get(findEmployeeIndexById(id)));
		return "employeeEdit";
	}

	private int findEmployeeIndexById(long id) {
		long idx = 100;
		for (EmployeeDto e : thymeEmployees)
			if (e.getId() == id) {
				idx = thymeEmployees.indexOf(e);
				break;
			}
		return (int) idx;
	}

//	FONTOS: a HTML <form> részében van megadva, hogy ez a metódus fogja a modelben visszakapni az ojjektumot modelben
	@PostMapping("/updateEmployee")
	public String updateEmployee(EmployeeDto employee) {
		thymeEmployees.set(findEmployeeIndexById(employee.getId()), employee);
		return "redirect:employees";
	}

}
