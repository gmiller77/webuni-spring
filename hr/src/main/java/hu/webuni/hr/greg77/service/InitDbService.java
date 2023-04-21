package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Company;
import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.repository.CompanyRepository;
import hu.webuni.hr.greg77.repository.EmployeeRepository;
import jakarta.transaction.Transactional;

@Service
public class InitDbService {

	private List<Company> initCompanies = new ArrayList<>();
	private List<Employee> initEmployees = new ArrayList<>();
	
	{
		initEmployees.add(new Employee ("Alma Anna", "főnök", 1_000_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Alma András", "kisfőnök", 800_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Alma Anett", "HR munkatárs", 500_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Alma Aladár", "jó munkásember", 650_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Alma Eperke", "gyakornok", 350_000, LocalDateTime.now().minusMonths(130)));
		initCompanies.add(new Company ("11-11-111111", "Alma Kft.", "1234 Budapest",List.copyOf(initEmployees)));
		initEmployees.clear();
		
		initEmployees.add(new Employee ("Buborék Anna", "főnök", 1_000_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Buborék András", "kisfőnök", 800_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Buborék Anett", "HR munkatárs", 500_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Buborék Aladár", "jó munkásember", 650_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Buborék Eperke", "gyakornok", 350_000, LocalDateTime.now().minusMonths(130)));
		initCompanies.add(new Company ("22-22-222222", "Buborék Kft.", "2345 Budapest",List.copyOf(initEmployees)));
		initEmployees.clear();
				
		initEmployees.add(new Employee ("Cérna Anna", "főnök", 1_000_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Cérna András", "kisfőnök", 800_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Cérna Anett", "HR munkatárs", 500_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Cérna Aladár", "jó munkásember", 650_000, LocalDateTime.now().minusMonths(130)));
		initEmployees.add(new Employee ("Cérna Eperke", "gyakornok", 350_000, LocalDateTime.now().minusMonths(130)));
		initCompanies.add(new Company ("33-33-333333", "Cérna Kft.", "3456 Budapest",List.copyOf(initEmployees)));
		initEmployees.clear();
	}
	
	public InitDbService() {
		super();
	}

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	public void clearDB() {
		companyRepository.deleteAll();
		employeeRepository.deleteAll();
	}

	@Transactional
	public void insertTestData() {
		for (Company company : initCompanies) {
			List<Employee> employees = company.getEmployees();
			if (employees != null) {
				for (Employee employee : employees) {
					employee.setCompany(company);
				}
			}
			companyRepository.save(company);
			if (employees != null) {
				employeeRepository.saveAll(employees);
			}
		}
	}
}
