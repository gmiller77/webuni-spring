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

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Mapper(componentModel = "spring")
public interface CompanyMapper {

	@Mapping(source = "employees", target = "employeeDtos")
	CompanyDto companyToDto(Company company);
	
	@Mapping(source = "employeeDtos", target = "employees")
	@Mapping(target = "companyType", ignore = true)
	Company dtoToCompany(CompanyDto companyDto);

	@Mapping(target = "employeeDtos", ignore = true)
	@Named("summary")
	CompanyDto companyToSummaryDto(Company company);
	
	@Mapping(source = "position.name", target = "position")
	@Mapping(target = "companyDto", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	@Mapping(target = "company", ignore = true)
	Employee dtoToEmployee(EmployeeDto employeeDto);

	List<Employee> dtosToEmployees(List<EmployeeDto> employeeDtos);

	List<CompanyDto> companiesToDtos(List<Company> companies);

	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToSummaryDtos(List<Company> companies);
}
