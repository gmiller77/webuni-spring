package hu.webuni.hr.greg77.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.greg77.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
//	Long countByIata(String iata);
//	
//	Long countByIataAndIdNot(String iata, long id);
}
