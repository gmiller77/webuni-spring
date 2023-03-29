package hu.webuni.hr.greg77.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.greg77.dto.CompanyDto;
import hu.webuni.hr.greg77.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();

	{
		companies.put(1L, new CompanyDto(1L, "11-11-111111", "Alma Kft.", "1234 Budapest"));
		companies.put(2L, new CompanyDto(2L, "22-22-222222", "Béta Kft.", "2345 Futapest"));
		companies.put(3L, new CompanyDto(3L, "33-33-333333", "Cirokseprű Kft.", "3456 Cegléd"));
		companies.put(4L, new CompanyDto(4L, "44-44-444444", "Dalmata Kft.", "4567 Dinnyés"));
	}

	
	/* WEEK-2 solution:
	 * @GetMapping public List<CompanyDto> getAll() { return new
	 * ArrayList<>(companies.values()); }
	 */
	
	// WEEK-3 / LIVE EVENT (ZOOM) solution for added requirements: "...?full=true" parameter
	@GetMapping
	public List<CompanyDto> getAll(@RequestParam Optional<Boolean> full) {
		return full.orElse(false)
				? new ArrayList<>(companies.values())
				: companies.values().stream().map(this::copyCompanyWithoutEmployees).toList();								
	}	
	
	/*
	megoldás JsonView-val
	@GetMapping(params = "full=true")
	public List<CompanyDto> getAllFull() {
		return new ArrayList<>(companies.values());
	}
	
	@GetMapping
	@JsonView(Views.BaseData.class)
	public List<CompanyDto> getAllFull(@RequestParam(required = false) Boolean full) {
		return new ArrayList<>(companies.values());
	}
	*/
		
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id) {
		CompanyDto companyDto = companies.get(id);
		if (companyDto != null)
			return ResponseEntity.ok(companyDto);
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}

	// LIVE EVENT (ZOOM) solution
	@PutMapping("/{id}")
	public CompanyDto modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		getCompanyOrThrow(id);		
		companyDto.setId(id);
		companies.put(id, companyDto);
		return companyDto;
	}
		
	/* OWN solution
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (!companies.containsKey(id))
			return ResponseEntity.notFound().build();
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	 }
	 */

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}
	
	// LIVE EVENT (ZOOM) solution
	@PostMapping("/{id}/employees")
	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		CompanyDto company = getCompanyOrThrow(id);
		company.getEmployees().add(employeeDto);
		return company;
	}
	
	// LIVE EVENT (ZOOM) solution
	@DeleteMapping("/{id}/employees/{employeeId}")
	public CompanyDto deleteEmployeeFromCompany(@PathVariable long id, @PathVariable long employeeId) {
		CompanyDto company = getCompanyOrThrow(id);
		company.getEmployees().removeIf(emp -> emp.getId() == employeeId);
		return company;
	}
	
	// LIVE EVENT (ZOOM) solution
	@PutMapping("/{id}/employees")
	public CompanyDto replaceAllEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> newEmployees) {
		CompanyDto company = getCompanyOrThrow(id);
		company.setEmployees(newEmployees);
		return company;
	}	
	
	private CompanyDto copyCompanyWithoutEmployees(CompanyDto company) {
		return new CompanyDto(company.getId(), company.getCompanyIdNumber(), company.getName(),
				company.getAddress(), null);
	}
	
	private CompanyDto getCompanyOrThrow(long id) {
		CompanyDto company = companies.get(id);
		if(company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);					
		}
		return company;
	}
}
