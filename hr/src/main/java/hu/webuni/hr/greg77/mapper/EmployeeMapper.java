package hu.webuni.hr.greg77.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.greg77.dto.EmployeeDto;
import hu.webuni.hr.greg77.model.Employee;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	
	List<Employee> dtosToEmployees (List<EmployeeDto> employeeDtos);

	@Mapping(source = "position.name", target = "position")
	@Mapping(target = "companyDto", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	@Mapping(target = "company", ignore = true)
	Employee dtoToEmployee(EmployeeDto employeeDto);
}
