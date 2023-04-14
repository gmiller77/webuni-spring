package hu.webuni.hr.greg77.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import hu.webuni.hr.greg77.dto.EmployeeDto;
import hu.webuni.hr.greg77.mapper.EmployeeMapper;
import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.repository.EmployeeRepository;
import hu.webuni.hr.greg77.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@GetMapping
	public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary){
		List<Employee> employees = null;
		if(minSalary == null) {
			employees = employeeService.findAll();
		} else {
			employees = employeeRepository.findBySalaryGreaterThanEqual(minSalary);
		}
		return employeeMapper.employeesToDtos(employees);
	}
	/*
	@GetMapping
	public List<EmployeeDto> getAll() {
		return new ArrayList<>(employeeMapper.employeesToDtos(employeeService.findAll()));
	}
	*/
	
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = findByIdOrThrow(id);
		return employeeMapper.employeeToDto(employee);
	}
	/*
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return employeeMapper.employeeToDto(employee);
	}
	*/
	
	private Employee findByIdOrThrow(long id) {
		return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
	}
	/*
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));				
		return employeeMapper.employeeToDto(employee);
	}
	*/
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		employeeDto.setId(id);
		Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
		if(updatedEmployee == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(employeeMapper.employeeToDto(updatedEmployee));
		}

	}
	/*
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		employee.setId(id);
		try {
			EmployeeDto savedEmployeeDto = employeeMapper.employeeToDto(employeeService.update(employee));
			return ResponseEntity.ok(savedEmployeeDto);
		}catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	*/
	
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.delete(id);
	}
	
	/*
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.delete(id);
	}
	*/

	// @PathVariable megoldás - ez működött, ez lett elfogadva
	@GetMapping("/salaryFilter/{query}")
	public List<EmployeeDto> getAllEmployeeSalaryGreaterThan(@PathVariable int query) {
		return employeeMapper.employeesToDtos(employeeService.findBySalaryGreaterThanEqual(query));
	/*	
		return employeeMapper.employeesToDtos(
				employeeService.findAll()
				.stream()
				.filter(e -> e.getSalary() >= query)
				.collect(Collectors.toList())
				);
	*/
	}

	// @RequestParam-os megoldás
	@GetMapping("/FilterBySalary")	
	public List<EmployeeDto> getAllEmployeeSalaryGreaterThan2(@RequestParam("salaryMin") int limit) {
		return employeeMapper.employeesToDtos(employeeService.findBySalaryGreaterThanEqual(limit));
		/*
		return employeeMapper.employeesToDtos(
				employeeService.findAll()
				.stream()
				.filter(e -> e.getSalary() >= limit)
				.collect(Collectors.toList())
				);
		*/
	}
	
//	@GetMapping("/position")	
	@GetMapping(value = "/search", params = "position")	
	public List<EmployeeDto> getAllEmployeesByPosition(@RequestParam String position) {
		return new ArrayList<>(employeeMapper.employeesToDtos(employeeService.findByPosition(position)));
	}
	
//	@GetMapping("/name")	
	@GetMapping(value = "/search", params = "nameStartsWith")	
	public List<EmployeeDto> getAllEmployeesByNamesLike(@RequestParam String nameStartsWith) {
		return new ArrayList<>(employeeMapper.employeesToDtos(employeeService.findByNameStartsWith(nameStartsWith)));
	}

//	@GetMapping("/startDateBetween")
	@GetMapping(value = "/search", params = {"startDate", "endDate"})
	public List<EmployeeDto> getAllEmployeesByStartDateBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
		return new ArrayList<>(employeeMapper.employeesToDtos(employeeService.findByStartDateBetweenDates(startDate, endDate)));
	}
}
