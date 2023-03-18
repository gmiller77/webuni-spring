package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.config.HrConfigProperties;
import hu.webuni.hr.greg77.config.HrConfigProperties.SmartRaisePercent;
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

//		System.out.println(raisePercent);
//		System.out.println(config.getRaise().getSmart().getPercents().toString());
		for (Entry<String, SmartRaisePercent> entry : config.getRaise().getSmart().getPercents().entrySet()) {
//			System.out.println(months + " hónap vs. vizsgálva: " + entry.getValue().getLimit());
			if (months >= entry.getValue().getLimit()) {
				raisePercent = entry.getValue().getPercent();
//				System.out.println("csere! " + raisePercent + " break");
				break;
			}
		}

		/*
		 * with @Value solution: int raisePercent = defaultPercent; long months =
		 * ChronoUnit.MONTHS.between(employee.getStartDate(), LocalDateTime.now());
		 * 
		 * if (months >= smartLimit1) raisePercent = smartPercent1; else if (months >=
		 * smartLimit2) raisePercent=smartPercent2; else if (months >= smartLimit3)
		 * raisePercent=smartPercent3;
		 */
		return raisePercent;
	}

}
