package hu.webuni.hr.greg77.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.greg77.service.DefaultEmployeeService;
import hu.webuni.hr.greg77.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultSalaryConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}

}
