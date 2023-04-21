package hu.webuni.hr.greg77.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private Long id;
	private String companyIdNumber;		//cégjegyzékszám 99-99-999999 formátum
	private String name;
	private String address;

//	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(mappedBy = "company")
	private List<Employee> employees;
//	private List<Employee> employees = new ArrayList<>();
	
	public Company() {
	}

	/*
	public Company(Long id, String companyIdNumber, String name, String address) {
		this.id = id;
		this.companyIdNumber = companyIdNumber;
		this.name = name;
		this.address = address;
	}
	*/

	public Company(Long id, String companyIdNumber, String name, String address, List<Employee> employees) {
		this.id = id;
		this.companyIdNumber = companyIdNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public Company(String companyIdNumber, String name, String address, List<Employee> employees) {
		super();
		this.companyIdNumber = companyIdNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public void addEmployee(Employee employee) {
		if(this.employees == null)
			this.employees = new ArrayList<>();
		this.employees.add(employee);
		employee.setCompany(this);
	}

}
