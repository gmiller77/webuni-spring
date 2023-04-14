package hu.webuni.hr.greg77.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.greg77.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByPosition(String position);
	
	List<Employee> findByNameStartingWithIgnoreCase(String nameStartsWith);
	
	List<Employee> findByStartDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
	
	List<Employee> findBySalaryGreaterThanEqual(Integer minSalary);
	
}
