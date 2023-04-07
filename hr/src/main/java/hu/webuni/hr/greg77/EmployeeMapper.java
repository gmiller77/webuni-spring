package hu.webuni.hr.greg77;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.greg77.dto.EmployeeDto;
import hu.webuni.hr.greg77.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	
	List<Employee> dtosToEmployees (List<EmployeeDto> employeeDtos);

	EmployeeDto employeeToDto(Employee employee);

	Employee dtoToEmployee(EmployeeDto employeeDto);
}
