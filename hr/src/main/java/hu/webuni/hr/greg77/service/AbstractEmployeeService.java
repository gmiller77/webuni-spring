package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.webuni.hr.greg77.model.Employee;

public abstract class AbstractEmployeeService implements EmployeeService {

	private Map<Long, Employee> employees = new HashMap<>();

	{
		employees.put(1L, new Employee(1L, "Anna Smith", "chief", 30_000, LocalDateTime.now().minusMonths(130)));
		employees.put(2L, new Employee(2L, "Bob Tailor", "assistant", 12_000, LocalDateTime.now().minusMonths(119)));
		employees.put(3L,
				new Employee(3L, "Charles Adams", "section head", 24_000, LocalDateTime.now().minusMonths(74)));
		employees.put(4L,
				new Employee(4L, "Diane Kerrigan", "adjutant", 15_000, LocalDateTime.now().minusMonths(55)));
		employees.put(5L, new Employee(5L, "Eric Tesla", "technician", 8_000, LocalDateTime.now().minusMonths(28)));
	}
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee save(Employee employee) {
		employees.put(employee.getId(), employee);
		return employee;
	}

	@Override
	public List<Employee> findAll() {
		return new ArrayList<>(employees.values());
	}

	@Override
	public Employee findById(long id) {
		return employees.get(id);
	}

	@Override
	public Employee put(long id, Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		employees.remove(id);
	}

}
