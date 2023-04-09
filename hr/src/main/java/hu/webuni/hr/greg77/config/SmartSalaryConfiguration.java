package hu.webuni.hr.greg77.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.greg77.service.EmployeeService;
import hu.webuni.hr.greg77.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartSalaryConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}

}
