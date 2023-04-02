package hu.webuni.hr.greg77.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
	
	private long id;
	private String companyIdNumber;
	private String name;
	private String address;
	private List<Employee> employees = new ArrayList<>();

	public Company() {
	}

	public Company(long id, String companyIdNumber, String name, String address) {
		this.id = id;
		this.companyIdNumber = companyIdNumber;
		this.name = name;
		this.address = address;
	}

	public Company(long id, String companyIdNumber, String name, String address, List<Employee> employees) {
		this.id = id;
		this.companyIdNumber = companyIdNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
