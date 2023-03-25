package hu.webuni.hr.greg77.model;

import java.time.LocalDateTime;

public class Employee {
	private long id;
	private String name;
	private String position;
	private int salary;
	private LocalDateTime startDate;

	public Employee(long id, String name, String position, int salary, LocalDateTime startDate) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
