package hu.webuni.hr.greg77.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Employee;

@Service
public class SalaryService {

	//metódus paraméterül kap egy alkalmazottat
	//emeli a fizetését
	//ehhez egy EmplService-t használ, injektál, és ettől kéri el, hogy ?% fizetésemelés jár az alkalmazottnak
	
	private EmployeeService employeeService;
	
	public SalaryService (EmployeeService employeeService) {		
		this.employeeService = employeeService;
	}
	/*
	public void setNewSalary(Employee employee) {
		employee.setSalary((int)(employee.getSalary()*(1.0+employeeService.getPayRaisePercent(employee))));
	*/
	
	public int getNewSalary(Employee employee) {
		return (int)(employee.getSalary()*(100+employeeService.getPayRaisePercent(employee))/100.0);
		//return employeeService.getPayRaisePercent(employee);
	}
}
