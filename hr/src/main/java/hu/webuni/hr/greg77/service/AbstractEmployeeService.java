package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.repository.EmployeeRepository;

public abstract class AbstractEmployeeService implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionService positionService;
	
	@Override
	@Transactional
	public Employee save(Employee employee) {
		if(employee.getId() != null && employee.getId() != 0L)
			return null;
		positionService.setPosition(employee);
		return employeeRepository.save(employee);
	}

	private void clearCompanyAndSetPosition(Employee employee) {
		employee.setCompany(null);
		positionService.setPosition(employee);
	}		

	@Override
	@Transactional
	public Employee update(Employee employee) {
		if(!employeeRepository.existsById(employee.getId()))
			return null;
		clearCompanyAndSetPosition(employee);
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(long id) {		
		return employeeRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}
	
	@Override
	public List<Employee> findBySalaryGreaterThanEqualX(Integer minSalary) {
		return employeeRepository.findBySalaryGreaterThanEqual(minSalary);
	}
	
	@Override
	public Page<Employee> findBySalaryGreaterThanEqual(Integer minSalary, Pageable pageable) {
		return employeeRepository.findBySalaryGreaterThanEqual(minSalary, pageable);
	}
	
	/*
	public List<Employee> findByPosition(String pos) {
		return employeeRepository.findByPosition(pos);
	}
	*/
	
	public List<Employee> findByNameStartsWith(String nameStartsWith) {
		return employeeRepository.findByNameStartingWithIgnoreCase(nameStartsWith);
	}
	
	public List<Employee> findByStartDateBetweenDates(LocalDateTime dateStart, LocalDateTime dateEnd){
		return employeeRepository.findByStartDateBetween(dateStart, dateEnd);
	}
	
}
/*
	-----------
	@Override
	@Transactional
	public Employee save(Employee employee) {
		if (employee.getId() != null && employee.getId() != 0L)
			return null;
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public Employee update(Employee employee) {
		if(!employeeRepository.existsById(employee.getId()))
			return null;
		return employeeRepository.save(employee);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
		

	public Optional<Employee> findById(long id) {		
		return employeeRepository.findById(id);
	}

	public void delete(long id) {
		employeeRepository.deleteById(id);
	}	
	
	public List<Employee> findBySalaryGreaterThanX(Integer minSalary) {
		return employeeRepository.findBySalaryGreaterThanEqual(minSalary);
	}
	
	public List<Employee> findByPosition(String pos) {
		return employeeRepository.findByPosition(pos);
	}
	
	public List<Employee> findByNameStartsWith(String nameStartsWith) {
		return employeeRepository.findByNameStartingWithIgnoreCase(nameStartsWith);
	}
	
	public List<Employee> findByStartDateBetweenDates(LocalDateTime dateStart, LocalDateTime dateEnd){
		return employeeRepository.findByStartDateBetween(dateStart, dateEnd);
	}
	
	
}
*/
