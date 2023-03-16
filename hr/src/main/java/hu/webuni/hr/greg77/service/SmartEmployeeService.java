package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.config.HrConfigProperties;
import hu.webuni.hr.greg77.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {

//	@Value("${smart.limit1}")
//	private int smartLimit1;
//	
//	@Value("${smart.percent1}")
//	private int smartPercent1;
//	
//	@Value("${smart.limit2}")
//	private int smartLimit2;
//	
//	@Value("${smart.percent2}")
//	private int smartPercent2;
//	
//	@Value("${smart.limit3}")
//	private int smartLimit3;
//	
//	@Value("${smart.percent3}")
//	private int smartPercent3;
//	
//	@Value("${smart.defaultPercent}")
//	private int defaultPercent;

	@Autowired
	HrConfigProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		
		
		int raisePercent = config.getRaise().getSmart().getDefaultPercent();
		long months = ChronoUnit.MONTHS.between(employee.getStartDate(), LocalDateTime.now());
		
		
		
		for (int limitIterator iterator = config.getRaise().getSmart().getLimits(); iterator.hasNext();) {
			
			type type = (type) iterator.next();
			
		}
		if (months >= config.getRaise().getSmart().getLimit1()) raisePercent = config.getRaise().getSmart().getPercent1();
			else if (months >= config.getRaise().getSmart().getLimit2()) raisePercent = config.getRaise().getSmart().getPercent2();
			else if (months >= config.getRaise().getSmart().getLimit3()) raisePercent = config.getRaise().getSmart().getPercent3();
		
		/*
		with @Value solution:
		int raisePercent = defaultPercent;
		long months = ChronoUnit.MONTHS.between(employee.getStartDate(), LocalDateTime.now());
		
		if (months >= smartLimit1) raisePercent = smartPercent1;
			else if (months >= smartLimit2) raisePercent=smartPercent2;
			else if (months >= smartLimit3) raisePercent=smartPercent3;
		*/
		return raisePercent;
	}

}
