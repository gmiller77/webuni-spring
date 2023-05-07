package hu.webuni.hr.greg77.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.service.CompanyService;
import hu.webuni.hr.greg77.service.EmployeeService;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CompanyService companyService;

	@GetMapping("/payRaisePercent")
	public int getPayRaisePercent(@RequestBody Employee employee) {
		return employeeService.getPayRaisePercent(employee);
	}
	
	@PutMapping("/{position}/raiseMin/{minSalary}/{companyId}")
	public void raiseMinSalary(@PathVariable String position, @PathVariable int minSalary, @PathVariable long companyId) {
		companyService.setMinSalary(position, companyId, minSalary);
	}
}
