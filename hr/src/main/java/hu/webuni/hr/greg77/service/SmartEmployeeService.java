package hu.webuni.hr.greg77.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.config.HrConfigProperties;
import hu.webuni.hr.greg77.config.HrConfigProperties.SmartRaisePercent;
import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.repository.EmployeeRepository;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

	public SmartEmployeeService(EmployeeRepository employeeRepository) {
		super(employeeRepository);
	}

	public SmartEmployeeService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		int raisePercent = config.getRaise().getSmart().getDefaultPercent();
		long months = ChronoUnit.MONTHS.between(employee.getStartDate(), LocalDateTime.now());

		for (Entry<String, SmartRaisePercent> entry : config.getRaise().getSmart().getPercents().entrySet()) {
			if (months >= entry.getValue().getLimit()) {
				raisePercent = entry.getValue().getPercent();
				break;
			}
		}
		return raisePercent;
	}

}
