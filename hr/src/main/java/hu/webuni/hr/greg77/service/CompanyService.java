package hu.webuni.hr.greg77.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.greg77.model.Company;

@Service
public class CompanyService {

	private Map<Long, Company> companies = new HashMap<>();

	{
		companies.put(1L, new Company(1L, "11-11-111111", "Alma Kft.", "1234 Budapest"));
		companies.put(2L, new Company(2L, "22-22-222222", "Béta Kft.", "2345 Futapest"));
		companies.put(3L, new Company(3L, "33-33-333333", "Cirokseprű Kft.", "3456 Cegléd"));
		companies.put(4L, new Company(4L, "44-44-444444", "Dalmata Kft.", "4567 Dinnyés"));
	}
	
	public Company save(Company company){
		checkUniqueCompanyIdNumber(company.getCompanyIdNumber());
		companies.put(company.getId(), company);
		return company;
	}
	
	public List<Company> findAll(){
		return new ArrayList<>(companies.values());		
	}
	
	public Company findById(long id) {
		return companies.get(id);
	}
	
	public Company put(long id, Company company) {
		getCompanyOrThrow(id);		
		company.setId(id);		
		companies.put(id, company);
		return company;
	}
	
	public void delete(long id) {
		companies.remove(id);
	}
	
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
}
