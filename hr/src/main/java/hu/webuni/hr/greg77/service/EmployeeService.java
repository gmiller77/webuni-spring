package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hu.webuni.hr.greg77.model.Employee;

public interface EmployeeService {
	
	int getPayRaisePercent(Employee employee);
	
	public Employee save(Employee employee);
	
	List<Employee> findAll();
	
	Optional<Employee> findById(long id);
	
	Employee update(Employee employee);
	
	public void delete(long id);
	
	Page<Employee> findBySalaryGreaterThanEqual(Integer minSalary, Pageable pageable);

	List<Employee> findBySalaryGreaterThanEqualX(Integer minSalary);
	
	List<Employee> findByPosition(String position);
	
	List<Employee> findByNameStartsWith(String nameStartsWith);
	
	List<Employee> findByStartDateBetweenDates(LocalDateTime dateStart, LocalDateTime dateEnd);
}
 