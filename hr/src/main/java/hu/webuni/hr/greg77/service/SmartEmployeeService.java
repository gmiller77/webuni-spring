package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Employee;

@Service
@Primary
public class SmartEmployeeService implements EmployeeService {

	@Override
	public int getPayRaisePercent(Employee employee) {
		// TODO Auto-generated method stub
		int raisePercent = 0;
		long months = ChronoUnit.MONTHS.between(employee.getStartDate(), LocalDateTime.now());
		
		if (months >= 120) raisePercent = 10;
			else if (months >= 60) raisePercent=5;
			else if (months >= 30) raisePercent=2;
		
		return raisePercent;
	}

}
