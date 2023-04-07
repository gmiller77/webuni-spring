package hu.webuni.hr.greg77.service;

import java.util.List;

import hu.webuni.hr.greg77.model.Employee;

public interface EmployeeService {
	
	
	
	public int getPayRaisePercent(Employee employee);
	
	public Employee save(Employee employee);
	
	public List<Employee> findAll();
	
	public Employee findById(long id);
	
	public Employee update(long id, Employee employee);
	
	public void delete(long id);
}
 