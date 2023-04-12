package hu.webuni.hr.greg77.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.greg77.dto.CompanyDto;
import hu.webuni.hr.greg77.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	List<CompanyDto> companiesToDtos(List<Company> companies);

	CompanyDto companyToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);
}
