package hu.webuni.hr.greg77.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService {

	@Override
	public int getPayRaisePercent(Employee employee) {
		// TODO Auto-generated method stub
		return 5;
	}

}
