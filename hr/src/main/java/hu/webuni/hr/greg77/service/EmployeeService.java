package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import hu.webuni.hr.greg77.model.Employee;

public interface EmployeeService {
	
	int getPayRaisePercent(Employee employee);
	
	public Employee save(Employee employee);
	
	public List<Employee> findAll();
	
	public Optional<Employee> findById(long id);
	
	public Employee update(Employee employee);
	
	public void delete(long id);
	
	List<Employee> findByPosition(String position);
	
	List<Employee> findByNameStartsWith(String nameStartsWith);
	
	List<Employee> findByStartDateBetweenDates(LocalDateTime dateStart, LocalDateTime dateEnd);
}
 