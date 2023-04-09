package hu.webuni.hr.greg77.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.repository.EmployeeRepository;
import jakarta.transaction.Transactional;

public abstract class AbstractEmployeeService implements EmployeeService {

	EmployeeRepository employeeRepository;

	/*
	 * private Map<Long, Employee> employees = new HashMap<>();
	 * 
	 * { employees.put(1L, new Employee(1L, "Anna Smith", "chief", 30_000,
	 * LocalDateTime.now().minusMonths(130))); employees.put(2L, new Employee(2L,
	 * "Bob Tailor", "assistant", 12_000, LocalDateTime.now().minusMonths(119)));
	 * employees.put(3L, new Employee(3L, "Charles Adams", "section head", 24_000,
	 * LocalDateTime.now().minusMonths(74))); employees.put(4L, new Employee(4L,
	 * "Diane Kerrigan", "adjutant", 15_000, LocalDateTime.now().minusMonths(55)));
	 * employees.put(5L, new Employee(5L, "Eric Tesla", "technician", 8_000,
	 * LocalDateTime.now().minusMonths(28))); }
	 */

	public abstract int getPayRaisePercent(Employee employee);

	public AbstractEmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Transactional
	public Employee update(Employee employee) {
		if (employeeRepository.existsById(employee.getId()))
			return employeeRepository.save(employee);
		else
			throw new NoSuchElementException();
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Transactional
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}
	
}
