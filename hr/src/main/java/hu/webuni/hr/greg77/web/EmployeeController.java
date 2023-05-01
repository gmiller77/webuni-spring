package hu.webuni.hr.greg77.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import hu.webuni.hr.greg77.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeMapper employeeMapper;

	/*
	@Autowired
	EmployeeRepository employeeRepository;
	*/

	@GetMapping
	public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary, Pageable pageable){
		List<Employee> employees = null;
		if(minSalary == null) {
			employees = employeeService.findAll();
		} else {
			Page<Employee> pageOfEmployees = employeeService.findBySalaryGreaterThanEqual(minSalary, pageable);
//			Page<Employee> pageOfEmployees = new PageImpl<>(employeeService.findBySalaryGreaterThanEqual(minSalary, pageable));
//			Page<Employee> pageOfEmployees = (Page<Employee>) employeeService.findBySalaryGreaterThanEqual(minSalary);
			System.out.println(pageOfEmployees.getTotalElements());
			System.out.println(pageOfEmployees.isFirst());
			System.out.println(pageOfEmployees.isLast());
			System.out.println(pageOfEmployees.hasNext());
			System.out.println(pageOfEmployees.hasPrevious());
			employees = pageOfEmployees.getContent();
		}
		return employeeMapper.employeesToDtos(employees);
	}

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = findByIdOrThrow(id);
		return employeeMapper.employeeToDto(employee);
	}

	private Employee findByIdOrThrow(long id) {
		return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
	}
	/*
	 * @PostMapping public EmployeeDto createEmployee(@RequestBody @Valid
	 * EmployeeDto employeeDto) { Employee employee =
	 * employeeService.save(employeeMapper.dtoToEmployee(employeeDto)); return
	 * employeeMapper.employeeToDto(employee); }
	 */

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id,
			@RequestBody @Valid EmployeeDto employeeDto) {
		employeeDto.setId(id);
		Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
		if (updatedEmployee == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(employeeMapper.employeeToDto(updatedEmployee));
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.delete(id);
	}

	// @PathVariable megoldás - ez működött, ez lett elfogadva
	@GetMapping("/salaryFilter/{query}")
	public List<EmployeeDto> getAllEmployeeSalaryGreaterThan(@PathVariable int query) {
		return employeeMapper.employeesToDtos(employeeService.findBySalaryGreaterThanEqualX(query));
		
//		return employeeMapper.employeesToDtos( employeeService.findAll() .stream()
//		.filter(e -> e.getSalary() >= query) .collect(Collectors.toList()) );
		
	}

	// @RequestParam-os megoldás
	@GetMapping("/FilterBySalary")
	public List<EmployeeDto> getAllEmployeeSalaryGreaterThan2(@RequestParam("salaryMin") int limit) {
		return employeeMapper.employeesToDtos(employeeService.findBySalaryGreaterThanEqualX(limit));
		
//		return employeeMapper.employeesToDtos( employeeService.findAll() .stream()
//		.filter(e -> e.getSalary() >= limit) .collect(Collectors.toList()) );
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
	@GetMapping(value = "/search", params = { "startDate", "endDate" })
	public List<EmployeeDto> getAllEmployeesByStartDateBetweenDates(@RequestParam LocalDateTime startDate,
			@RequestParam LocalDateTime endDate) {
		return new ArrayList<>(
				employeeMapper.employeesToDtos(employeeService.findByStartDateBetweenDates(startDate, endDate)));
	}
}
