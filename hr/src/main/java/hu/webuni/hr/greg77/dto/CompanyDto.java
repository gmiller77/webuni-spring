package hu.webuni.hr.greg77.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

public class CompanyDto {

	@PositiveOrZero
	private long id;
	@NotEmpty
	private String companyIdNumber;
	@NotEmpty
	private String name;
	@NotEmpty
	private String address;
//	private List<EmployeeDto> employeeDtos;
	private List<EmployeeDto> employeeDtos = new ArrayList<>();

	public CompanyDto() {
//		super();
	}

	/*
	public CompanyDto(@PositiveOrZero long id, @NotEmpty String companyIdNumber, @NotEmpty String name,
			@NotEmpty String address) {
		super();
		this.id = id;
		this.companyIdNumber = companyIdNumber;
		this.name = name;
		this.address = address;
	}
	*/

	public CompanyDto(@PositiveOrZero long id, @NotEmpty String companyIdNumber, @NotEmpty String name,
			@NotEmpty String address, List<EmployeeDto> employeeDtos) {
		super();
		this.id = id;
		this.companyIdNumber = companyIdNumber;
		this.name = name;
		this.address = address;
		this.employeeDtos = employeeDtos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyIdNumber() {
		return companyIdNumber;
	}

	public void setCompanyIdNumber(String companyIdNumber) {
		this.companyIdNumber = companyIdNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EmployeeDto> getEmployeeDtos() {
		return employeeDtos;
	}

	public void setEmployeeDtos(List<EmployeeDto> employeeDtos) {
		this.employeeDtos = employeeDtos;
	}

}
