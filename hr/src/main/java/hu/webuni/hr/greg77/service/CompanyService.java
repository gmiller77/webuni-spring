package hu.webuni.hr.greg77.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Company;
import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.repository.CompanyRepository;
import hu.webuni.hr.greg77.repository.EmployeeRepository;

@Service
public class CompanyService {

	
	/*
	private Map<Long, Company> companies = new HashMap<>();

	{
		companies.put(1L, new Company(1L, "11-11-111111", "Alma Kft.", "1234 Budapest"));
		companies.put(2L, new Company(2L, "22-22-222222", "Béta Kft.", "2345 Futapest"));
		companies.put(3L, new Company(3L, "33-33-333333", "Cirokseprű Kft.", "3456 Cegléd"));
		companies.put(4L, new Company(4L, "44-44-444444", "Dalmata Kft.", "4567 Dinnyés"));
	}
	*/
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
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
	
	public void delete(long id) {
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
	
	/*
	private void checkUniqueCompanyIdNumber(String companyIdNumber) {
		Optional<Company> companyWithSameIdNumber = companies.values().stream().filter(a -> a.getCompanyIdNumber().equals(companyIdNumber))
				.findAny();
		if (companyWithSameIdNumber.isPresent())
			throw new NonUniqueCompanyIdNumberException(companyIdNumber);
	}
	
	private Company getCompanyOrThrow(long id) {
		Company company = companies.get(id);
		if(company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);					
		}
		return company;
	}
	*/
}
