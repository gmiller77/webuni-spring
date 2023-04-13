package hu.webuni.hr.greg77.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.greg77.dto.CompanyDto;
import hu.webuni.hr.greg77.dto.EmployeeDto;
import hu.webuni.hr.greg77.model.Company;
import hu.webuni.hr.greg77.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
CompanyDto companyToDto(Company company);
	
	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToSummaryDto(Company company);
	
	Company dtoToCompany(CompanyDto companyDto);
	
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> employeeDtos);
	
	List<CompanyDto> companiesToDtos(List<Company> companies);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToSummaryDtos(List<Company> companies);
}
