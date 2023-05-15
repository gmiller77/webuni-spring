package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Company;
import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.model.Position;
import hu.webuni.hr.greg77.model.PositionDetailsByCompany;
import hu.webuni.hr.greg77.model.Qualification;
import hu.webuni.hr.greg77.repository.CompanyRepository;
import hu.webuni.hr.greg77.repository.EmployeeRepository;
import hu.webuni.hr.greg77.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.greg77.repository.PositionRepository;
import jakarta.transaction.Transactional;

@Service
public class InitDbService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private List<Company> initCompanies = new ArrayList<>();
	private List<Employee> initEmployees = new ArrayList<>();
	private Map<String, Position> initPositions = new HashMap<>();
	private List<PositionDetailsByCompany> initPositionDetailsByCompany = new ArrayList<>();
	
	{
		/*
		initPositions.put("fonok", new Position("főnök", Qualification.UNIVERSITY));
		initPositions.put("kisfonok", new Position("kisfőnök", Qualification.UNIVERSITY));
		initPositions.put("hr", new Position("HR munkatárs", Qualification.COLLEGE));
		initPositions.put("jomunkas", new Position("jó munkásember", Qualification.HIGH_SCHOOL));
		initPositions.put("gyakornok", new Position("gyakornok", Qualification.COLLEGE));
		
//		ez a 3 employee lesz ellátva username / password-del:
		
		Employee empWithPwd_1 = new Employee ("Alma Anna", initPositions.get("fonok"), 900_000, LocalDateTime.now().minusMonths(140));
		empWithPwd_1.setUsername("user1");
		empWithPwd_1.setPassword(passwordEncoder.encode("pass1"));
		initEmployees.add(empWithPwd_1);

		Employee empWithPwd_2 = new Employee ("Alma András", initPositions.get("kisfonok"), 750_000, LocalDateTime.now().minusMonths(100));
		empWithPwd_2.setUsername("user2");
		empWithPwd_2.setPassword(passwordEncoder.encode("pass2"));
		initEmployees.add(empWithPwd_2);

		Employee empWithPwd_3 = new Employee ("Alma Anett", initPositions.get("hr"), 450_000, LocalDateTime.now().minusMonths(80));
		empWithPwd_3.setUsername("user3");
		empWithPwd_3.setPassword(passwordEncoder.encode("pass3"));
		initEmployees.add(empWithPwd_3);
		
		initEmployees.add(new Employee ("Alma Aladár", initPositions.get("jomunkas"), 620_000, LocalDateTime.now().minusMonths(20)));
		initEmployees.add(new Employee ("Alma Eperke", initPositions.get("gyakornok"), 340_000, LocalDateTime.now().minusMonths(70)));
		Company company1 = new Company ("11-11-111111", "Alma Kft.", "1234 Budapest",List.copyOf(initEmployees)); 
		initCompanies.add(company1);
		
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_000_000, company1, initPositions.get("fonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (800_000, company1, initPositions.get("kisfonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (500_000, company1, initPositions.get("hr")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (500_000, company1, initPositions.get("jomunkas")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (300_000, company1, initPositions.get("gyakornok")));
		
		initEmployees.clear();
		
		initEmployees.add(new Employee ("Buborék Anna", initPositions.get("fonok"), 1_100_000, LocalDateTime.now().minusMonths(60)));
		initEmployees.add(new Employee ("Buborék András", initPositions.get("kisfonok"), 810_000, LocalDateTime.now().minusMonths(40)));
		initEmployees.add(new Employee ("Buborék Anett", initPositions.get("hr"), 510_000, LocalDateTime.now().minusMonths(20)));
		initEmployees.add(new Employee ("Buborék Aladár", initPositions.get("jomunkas"), 660_000, LocalDateTime.now().minusMonths(10)));
		initEmployees.add(new Employee ("Buborék Eperke", initPositions.get("gyakornok"), 355_000, LocalDateTime.now().minusMonths(40)));
		
		Company company2 = new Company ("22-22-222222", "Buborék Kft.", "2345 Budapest",List.copyOf(initEmployees));
		initCompanies.add(company2);

		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_000_000, company2, initPositions.get("fonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (850_000, company2, initPositions.get("kisfonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (550_000, company2, initPositions.get("hr")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (700_000, company2, initPositions.get("jomunkas")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (300_000, company2, initPositions.get("gyakornok")));
		
		initEmployees.clear();
		
		initEmployees.add(new Employee ("Cérna Anna", initPositions.get("fonok"), 1_200_000, LocalDateTime.now().minusMonths(20)));
		initEmployees.add(new Employee ("Cérna András", initPositions.get("kisfonok"), 900_000, LocalDateTime.now().minusMonths(30)));
		initEmployees.add(new Employee ("Cérna Anett", initPositions.get("hr"), 650_000, LocalDateTime.now().minusMonths(40)));
		initEmployees.add(new Employee ("Cérna Aladár", initPositions.get("jomunkas"), 750_000, LocalDateTime.now().minusMonths(50)));
		initEmployees.add(new Employee ("Cérna Eperke", initPositions.get("gyakornok"), 400_000, LocalDateTime.now().minusMonths(25)));
		Company company3 = new Company ("33-33-333333", "Cérna Kft.", "3456 Budapest",List.copyOf(initEmployees));
		initCompanies.add(company3);

		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_500_000, company3, initPositions.get("fonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_000_000, company3, initPositions.get("kisfonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (700_000, company3, initPositions.get("hr")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (800_000, company3, initPositions.get("jomunkas")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (300_000, company3, initPositions.get("gyakornok")));
		
		initEmployees.clear();
		*/
	}
	
	@Transactional
	public void clearDB() {
		positionDetailsByCompanyRepository.deleteAll();
		positionRepository.deleteAll();
		companyRepository.deleteAll();
		employeeRepository.deleteAll();
	}

	@Transactional
	public void insertTestData() {
		initPositions.put("fonok", new Position("főnök", Qualification.UNIVERSITY));
		initPositions.put("kisfonok", new Position("kisfőnök", Qualification.UNIVERSITY));
		initPositions.put("hr", new Position("HR munkatárs", Qualification.COLLEGE));
		initPositions.put("jomunkas", new Position("jó munkásember", Qualification.HIGH_SCHOOL));
		initPositions.put("gyakornok", new Position("gyakornok", Qualification.COLLEGE));
		
//		ez a 3 employee lesz ellátva username / password-del:
//		1.
		Employee empWithPwd_1 = new Employee ("Alma Anna", initPositions.get("fonok"), 900_000, LocalDateTime.now().minusMonths(140));
		empWithPwd_1.setUsername("user1");
		empWithPwd_1.setPassword(passwordEncoder.encode("pass1"));
		initEmployees.add(empWithPwd_1);
//		2.
		Employee empWithPwd_2 = new Employee ("Alma András", initPositions.get("kisfonok"), 750_000, LocalDateTime.now().minusMonths(100));
		empWithPwd_2.setUsername("user2");
		empWithPwd_2.setPassword(passwordEncoder.encode("pass2"));
		initEmployees.add(empWithPwd_2);
//		3.
		Employee empWithPwd_3 = new Employee ("Alma Anett", initPositions.get("hr"), 450_000, LocalDateTime.now().minusMonths(80));
		empWithPwd_3.setUsername("user3");
		empWithPwd_3.setPassword(passwordEncoder.encode("pass3"));
		initEmployees.add(empWithPwd_3);
		
		initEmployees.add(new Employee ("Alma Aladár", initPositions.get("jomunkas"), 620_000, LocalDateTime.now().minusMonths(20)));
		initEmployees.add(new Employee ("Alma Eperke", initPositions.get("gyakornok"), 340_000, LocalDateTime.now().minusMonths(70)));
		Company company1 = new Company ("11-11-111111", "Alma Kft.", "1234 Budapest",List.copyOf(initEmployees)); 
		initCompanies.add(company1);
		
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_000_000, company1, initPositions.get("fonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (800_000, company1, initPositions.get("kisfonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (500_000, company1, initPositions.get("hr")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (500_000, company1, initPositions.get("jomunkas")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (300_000, company1, initPositions.get("gyakornok")));
		
		initEmployees.clear();
		
		initEmployees.add(new Employee ("Buborék Anna", initPositions.get("fonok"), 1_100_000, LocalDateTime.now().minusMonths(60)));
		initEmployees.add(new Employee ("Buborék András", initPositions.get("kisfonok"), 810_000, LocalDateTime.now().minusMonths(40)));
		initEmployees.add(new Employee ("Buborék Anett", initPositions.get("hr"), 510_000, LocalDateTime.now().minusMonths(20)));
		initEmployees.add(new Employee ("Buborék Aladár", initPositions.get("jomunkas"), 660_000, LocalDateTime.now().minusMonths(10)));
		initEmployees.add(new Employee ("Buborék Eperke", initPositions.get("gyakornok"), 355_000, LocalDateTime.now().minusMonths(40)));
		
		Company company2 = new Company ("22-22-222222", "Buborék Kft.", "2345 Budapest",List.copyOf(initEmployees));
		initCompanies.add(company2);

		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_000_000, company2, initPositions.get("fonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (850_000, company2, initPositions.get("kisfonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (550_000, company2, initPositions.get("hr")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (700_000, company2, initPositions.get("jomunkas")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (300_000, company2, initPositions.get("gyakornok")));
		
		initEmployees.clear();
		
		initEmployees.add(new Employee ("Cérna Anna", initPositions.get("fonok"), 1_200_000, LocalDateTime.now().minusMonths(20)));
		initEmployees.add(new Employee ("Cérna András", initPositions.get("kisfonok"), 900_000, LocalDateTime.now().minusMonths(30)));
		initEmployees.add(new Employee ("Cérna Anett", initPositions.get("hr"), 650_000, LocalDateTime.now().minusMonths(40)));
		initEmployees.add(new Employee ("Cérna Aladár", initPositions.get("jomunkas"), 750_000, LocalDateTime.now().minusMonths(50)));
		initEmployees.add(new Employee ("Cérna Eperke", initPositions.get("gyakornok"), 400_000, LocalDateTime.now().minusMonths(25)));
		Company company3 = new Company ("33-33-333333", "Cérna Kft.", "3456 Budapest",List.copyOf(initEmployees));
		initCompanies.add(company3);

		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_500_000, company3, initPositions.get("fonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (1_000_000, company3, initPositions.get("kisfonok")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (700_000, company3, initPositions.get("hr")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (800_000, company3, initPositions.get("jomunkas")));
		initPositionDetailsByCompany.add(new PositionDetailsByCompany (300_000, company3, initPositions.get("gyakornok")));
		
		initEmployees.clear();
		
		for(Position position : initPositions.values()){
		    positionRepository.save(position);
		}
		
		for (Company company : initCompanies) {
			List<Employee> employees = company.getEmployees();
			if (employees != null) {
				for (Employee employee : employees) {
					employee.setCompany(company);
				}
				employeeRepository.saveAll(employees);
			}
			Company savedCompany= companyRepository.save(company);
			
			List<PositionDetailsByCompany> posFilteredList = 
					initPositionDetailsByCompany.stream().
					filter(f -> f.getCompany().getCompanyIdNumber().equals(savedCompany.getCompanyIdNumber()))
					.collect(Collectors.toList());
			if (posFilteredList != null) {
				for (PositionDetailsByCompany posDetails : posFilteredList) {
					posDetails.setCompany(savedCompany);
				}
				positionDetailsByCompanyRepository.saveAll(posFilteredList);
			}
		}
	}
}
