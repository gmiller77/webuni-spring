package hu.webuni.hr.greg77.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import hu.webuni.hr.greg77.CompanyMapper;
import hu.webuni.hr.greg77.EmployeeMapper;
import hu.webuni.hr.greg77.dto.CompanyDto;
import hu.webuni.hr.greg77.dto.EmployeeDto;
import hu.webuni.hr.greg77.model.Company;
import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.service.CompanyService;
import hu.webuni.hr.greg77.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
//	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/* WEEK-2 solution:
	 * @GetMapping public List<CompanyDto> getAll() { return new
	 * ArrayList<>(companies.values()); }
	 */
	
	// WEEK-3 / LIVE EVENT (ZOOM) solution for added requirements: "...?full=true" parameter
	@GetMapping
	public List<CompanyDto> getAll(@RequestParam Optional<Boolean> full) {		
		return full.orElse(false)
				? new ArrayList<>(companyMapper
									.companiesToDtos(companyService.findAll()))
				: new ArrayList<>(companyMapper
									.companiesToDtos(companyService.findAll()
									.stream()
									.map(this::copyCompanyWithoutEmployees)
									.toList()));
//				: companies.values().stream().map(this::copyCompanyWithoutEmployees).toList();								
	}	
	
//	/*
//	megoldás JsonView-val
//	@GetMapping(params = "full=true")
//	public List<CompanyDto> getAllFull() {
//		return new ArrayList<>(companies.values());
//	}
//	
//	@GetMapping
//	@JsonView(Views.BaseData.class)
//	public List<CompanyDto> getAllFull(@RequestParam(required = false) Boolean full) {
//		return new ArrayList<>(companies.values());
//	}
//	*/

 	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id) {
		Company company = companyService.findById(id);
		if (company != null)
			return companyMapper.companyToDto(company);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);		
	}

//	
//	@GetMapping("/{id}")
//	public CompanyDto getById(@PathVariable long id) {
//		CompanyDto companyDto = companies.get(id);
//		if (companyDto != null)
//			return companyDto;
//		else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);		
//	}
//
	@PostMapping
	public CompanyDto createCompany(@RequestBody @Valid CompanyDto companyDto) {
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));				
		return companyMapper.companyToDto(company);
	}
	
	@PutMapping("/{id}")
	public CompanyDto modifyCompany(@PathVariable long id, @RequestBody @Valid CompanyDto companyDto) {	
		Company company = companyService.put(id, companyMapper.dtoToCompany(companyDto));
		return companyMapper.companyToDto(company);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}
	
	// refactored from last week's LIVE EVENT (ZOOM) solution
	@PostMapping("/{id}/employees")
	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
//		CompanyDto company = getCompanyOrThrow(id);
		Company company = companyService.findById(id);
		Employee newEmployee = employeeMapper.dtoToEmployee(employeeDto);
		company.getEmployees().add(newEmployee);
		return companyMapper.companyToDto(company);
	}
	
	// LIVE EVENT (ZOOM) solution
	@DeleteMapping("/{id}/employees/{employeeId}")
	public CompanyDto deleteEmployeeFromCompany(@PathVariable long id, @PathVariable long employeeId) {
//		CompanyDto company = getCompanyOrThrow(id);
		Company company = companyService.findById(id);
		company.getEmployees().removeIf(emp -> emp.getId() == employeeId);
		return companyMapper.companyToDto(company);
	}
	
	// refactored from LIVE EVENT (ZOOM) solution
	@PutMapping("/{id}/employees")
	public CompanyDto replaceAllEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> newEmployeeDtos) {
//		CompanyDto company = getCompanyOrThrow(id);
		Company company = companyService.findById(id);		
		company.setEmployees(employeeMapper.dtosToEmployees(newEmployeeDtos));				
		return companyMapper.companyToDto(company);
	}	
	
	private Company copyCompanyWithoutEmployees(Company company) {
		return new Company(company.getId(), company.getCompanyIdNumber(), company.getName(),
				company.getAddress(), null);
	}
//	
	
}
