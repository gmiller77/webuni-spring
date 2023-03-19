package hu.webuni.hr.greg77.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.greg77.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class HrController {

	private Map<Long, EmployeeDto> employees = new HashMap<>();

	{
		employees.put(1L, new EmployeeDto(1L, "Anna Smith", "chief", 30_000, LocalDateTime.now().minusMonths(130)));
		employees.put(2L, new EmployeeDto(2L, "Bob Tailor", "assistant", 12_000, LocalDateTime.now().minusMonths(119)));
		employees.put(3L,
				new EmployeeDto(3L, "Charles Adams", "section head", 24_000, LocalDateTime.now().minusMonths(74)));
		employees.put(4L, new EmployeeDto(4L, "Diane Kerrigan", "adjutant", 15_000, LocalDateTime.now().minusMonths(55)));
		employees.put(5L, new EmployeeDto(5L, "Eric Tesla", "technician", 8_000, LocalDateTime.now().minusMonths(28)));
	}

	@GetMapping
	public List<EmployeeDto> getAll() {
		return new ArrayList<>(employees.values());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		EmployeeDto employeeDto = employees.get(id);
		if (employeeDto != null)
			return ResponseEntity.ok(employeeDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		employees.put(employeeDto.getId(), employeeDto);
		return employeeDto;
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if (!employees.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}

		employeeDto.setId(id);
		employees.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employees.remove(id);
	}

	@GetMapping("/salaryFilter/{query}")
	public Map<Long, EmployeeDto> getAllEmployeePaymentGreaterThan(@PathVariable int query) {
		Map<Long, EmployeeDto> resultMap = employees.
				entrySet().
				stream().
				filter(map -> map.getValue().getSalary() >= query).
				collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));	
		return resultMap;
	}
	
}
