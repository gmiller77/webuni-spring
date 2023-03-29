package hu.webuni.hr.greg77.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.service.EmployeeService;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {
	
	@Autowired
	EmployeeService employeeService;	

	@PostMapping("/payRaisePercent")
	public int getPayRaisePercent(@RequestBody Employee employee) {
		return employeeService.getPayRaisePercent(employee);
	}
}
