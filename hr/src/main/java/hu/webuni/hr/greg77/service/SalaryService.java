package hu.webuni.hr.greg77.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Employee;

@Service
public class SalaryService {

	private EmployeeService employeeService;
	
	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public int getNewSalary(Employee employee) {
		return (int) (employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100.0);
	}

	public void setNewSalary(Employee employee) {
		int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
		employee.setSalary(newSalary);
	}
}
