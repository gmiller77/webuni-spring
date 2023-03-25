package hu.webuni.hr.greg77.dto;

import java.util.List;

public class CompanyDto {
	private String Id;
	private String name;
	private String address;
	private List<EmployeeDto> employees;
	public CompanyDto(String id, String name, String address) {
		super();
		Id = id;
		this.name = name;
		this.address = address;
	}
	public CompanyDto() {
		super();
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
	public List<EmployeeDto> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
	
	
}
