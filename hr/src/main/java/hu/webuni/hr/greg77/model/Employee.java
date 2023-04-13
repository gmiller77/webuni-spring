package hu.webuni.hr.greg77.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String position;
	private int salary;
	private LocalDateTime startDate;

	@ManyToOne	
	private Company company;
	
	public Employee() {
		super();
	}

	public Employee(Long id, String name, String position, int salary, LocalDateTime startDate) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
//		String str = "ID: " + this.id + ", name: " + this.name + ", positon: " + this.position + ", salary: " + this.salary
//				+ ", started working: " + this.startDate.toLocalDate();
		var str = "|ID: " + String.format("%-4s", this.id) + "|name: " + String.format("%-20s", this.name)
				+ "|position: " + String.format("%-15s", this.position) + "|salary: "
				+ String.format("%5d", this.salary) + " €" + "|started: "
				+ String.format("%10s|", this.startDate.toLocalDate());
		return str;
	}
}
