package hu.webuni.hr.greg77.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.greg77.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	/*
	 * @Query("SELECT DISTINCT c.companyName, e.employeeName, e.salary " +
	 * "FROM Company c JOIN c.employees e " + "WHERE e.salary > :limit")
	 * List<Object[]> findCompaniesWithHighSalaryEmployees(@Param("limit")
	 * BigDecimal limit);
	 */
}
