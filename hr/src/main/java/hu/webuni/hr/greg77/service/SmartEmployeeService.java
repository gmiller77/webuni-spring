package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {

	@Value("${smart.limit1}")
	private int smartLimit1;
	
	@Value("${smart.percent1}")
	private int smartPercent1;
	
	@Value("${smart.limit2}")
	private int smartLimit2;
	
	@Value("${smart.percent2}")
	private int smartPercent2;
	
	@Value("${smart.limit3}")
	private int smartLimit3;
	
	@Value("${smart.percent3}")
	private int smartPercent3;
	
	@Value("${smart.defaultPercent}")
	private int defaultPercent;
		
	@Override
	public int getPayRaisePercent(Employee employee) {
		// TODO Auto-generated method stub
		int raisePercent = defaultPercent;
		long months = ChronoUnit.MONTHS.between(employee.getStartDate(), LocalDateTime.now());
		
		if (months >= smartLimit1) raisePercent = smartPercent1;
			else if (months >= smartLimit2) raisePercent=smartPercent2;
			else if (months >= smartLimit3) raisePercent=smartPercent3;
		
		return raisePercent;
	}

}
