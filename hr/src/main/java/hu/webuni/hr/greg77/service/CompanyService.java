package hu.webuni.hr.greg77.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.greg77.model.AverageSalaryByPosition;
import hu.webuni.hr.greg77.model.Company;
import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.repository.CompanyRepository;
import hu.webuni.hr.greg77.repository.EmployeeRepository;
import hu.webuni.hr.greg77.repository.PositionDetailsByCompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	
	public Company save(Company company) {
		if(company.getId() != null && company.getId() != 0L)
			return null;
		return companyRepository.save(company);
	}
	/*
	public Company save(Company company){
		checkUniqueCompanyIdNumber(company.getCompanyIdNumber());
		companies.put(company.getId(), company);
		return company;
	}
	*/
	
	public List<Company> findAll(Optional<Boolean> full) {
		if (full.orElse(false)) 
			return companyRepository.findAllWithEmployees();
		 	else 
			return companyRepository.findAll();
	}
	
	public Optional<Company> findById(long id, Optional<Boolean> full) {		
		if (full.orElse(false))
			return companyRepository.findByIdWithEmployees(id);
		else
			return companyRepository.findById(id);
	}
	
	public Company update(Company company) {
		if(!companyRepository.existsById(company.getId()))
			return null;
		return companyRepository.save(company);
	}
	
	/*
	public Company put(long id, Company company) {
		getCompanyOrThrow(id);		
		company.setId(id);		
		companies.put(id, company);
		return company;
	}
	*/
	
	@Transactional
	public void delete(long id) {
//		EmployeeService.updateCompanyVariableToNull(id);
		
		Company company = companyRepository.findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));

        for (Employee employee : company.getEmployees()) {
            employee.setCompany(null);
        }
		companyRepository.deleteById(id);
	}
	
	public Company addEmployee(long id, Employee employee) {
		Company company = companyRepository.findById(id).get();
		company.addEmployee(employee);
		employeeRepository.save(employee);
		return company;
	}
	
	public Company deleteEmployee(long id, long employeeId) {
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		return company;
	}
	
	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = companyRepository.findById(id).get();
		for(Employee emp: company.getEmployees()) {
			emp.setCompany(null);
		}
		company.getEmployees().clear();
		for(Employee emp: employees) {
			company.addEmployee(emp);
			employeeRepository.save(emp);
		}
		return company;
	}
	
	@Transactional
	@Modifying
	public void setMinSalary(String positionName, long companyId, int minSalary) {
		positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, companyId)
		.forEach(pd -> {
			pd.setMinSalary(minSalary);
			//1. megoldás: nem hatékony, mert minden employee-t betölt, és amiket módosítok egyesével UPDATE-eli SQL szinten
//			pd.getCompany().getEmployees().forEach( e-> {
//				if(e.getPosition().getName().equals(positionName)
//						&& e.getSalary() < minSalary) {
//					e.setSalary(minSalary);
//					//employeeRepository.save(e); --> nem szükséges a @Transactional miatt
//				}				
//			});
			//2. megoldás
			employeeRepository.updateSalaries(positionName, minSalary, companyId);
		});
	}
	
	public List<Company> findCompaniesWithEmployeeCountHigherThan(int aboveEmployeeCount) {
		return companyRepository.findCompaniesWithEmployeeCountHigherThan(aboveEmployeeCount);
	}

	public List<Company> findCompaniesWithHighSalaryEmployees(int aboveSalary) {
		return companyRepository.findCompaniesWithHighSalaryEmployees(aboveSalary);
	}
	
	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long id) {
		return companyRepository.findAverageSalariesByPosition(id);
	}

}
