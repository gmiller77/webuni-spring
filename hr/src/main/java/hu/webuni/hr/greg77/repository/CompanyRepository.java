package hu.webuni.hr.greg77.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.greg77.model.AverageSalaryByPosition;
import hu.webuni.hr.greg77.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	/*
	@Query("SELECT DISTINCT c.name, e.name, e.salary "
			+ "FROM Company c JOIN c.employees e "
//			+ "FROM Company c INNER JOIN Employee e ON c.id = e.company_id"
			+ "WHERE e.salary > :limit")
	public List<Company> findCompaniesWithHighSalaryEmployees(int limit);
	*/
	
	@EntityGraph(attributePaths = {"employees", "employees.position"})
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWithEmployees();
	
	@EntityGraph(attributePaths = {"employees", "employees.position"})
	@Query("SELECT c FROM Company c WHERE c.id=:id")
	public Optional<Company> findByIdWithEmployees(Long id);
	
	
	@Query("SELECT DISTINCT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary")
	public List<Company> findCompaniesWithHighSalaryEmployees(int minSalary);


	/*
	@Query("SELECT c.name, COUNT(e) as employeeCount "
			+ "FROM Company c JOIN c.employees e "
			+ "GROUP BY c.name HAVING COUNT(e) > :limit")
    public List<Company> findCompaniesWithEmployeeCountHigherThan(int limit);
    */
	
//	LIVE:
	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :minEmployeeCount")
	public List<Company> findCompaniesWithEmployeeCountHigherThan(int minEmployeeCount);

	/*
	@Query("SELECT e.position, AVG(e.salary) as averageSalary "
            + "FROM Company c JOIN c.employees e "
            + "WHERE c.id = :companyId "
            + "GROUP BY e.position "
            + "ORDER BY AVG(e.salary) DESC")
    public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);
	*/
	
//	LIVE:
	@Query("SELECT e.position.name AS position, avg(e.salary) AS averageSalary "
			+ "FROM Company c "
			+ "INNER JOIN c.employees e "
			+ "WHERE c.id=:companyId "
			+ "GROUP BY e.position.name "
			+ "ORDER BY avg(e.salary) DESC")
	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);
	
	
}
