package hu.webuni.hr.greg77.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.greg77.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	List<Employee> findByPosition(String position);

	List<Employee> findByNameStartingWithIgnoreCase(String nameStartsWith);

	List<Employee> findByStartDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);

	List<Employee> findBySalaryGreaterThanEqual(Integer minSalary);
	
	Page<Employee> findBySalaryGreaterThanEqual(Integer minSalary, Pageable pageable);

//	@Transactional //elhagyható, ha a service rétegre bízzuk a tranzakció indítását 
	@Modifying
	//1. megoldás: UPDATE + JOIN nem támogatott együtt
//	@Query("UPDATE Employee e "
//			+ "SET e.salary = :minSalary "
//			+ "WHERE e.position.name=:position "
//			+ "AND e.salary < :minSalary "
//			+ "AND e.company.id=:companyId")
	@Query("UPDATE Employee e "
			+ "SET e.salary = :minSalary "
			+ "WHERE e.id IN "
			+ "(SELECT e2.id "
			+ "FROM Employee e2 " 
			+ "WHERE " + "e2.position.name=:position " 
			+ "AND e2.salary < :minSalary "
			+ "AND e2.company.id=:companyId)")
	public int updateSalaries(String position, int minSalary, long companyId);

}
