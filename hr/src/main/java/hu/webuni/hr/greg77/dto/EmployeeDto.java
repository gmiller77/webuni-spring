package hu.webuni.hr.greg77.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

public class EmployeeDto {
	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String position;
	@Positive
	private int salary;
	@Past
	private LocalDateTime startDate;
//	private CompanyDto companyDto;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(long id, @NotEmpty String name, @NotEmpty String position, @Positive int salary,
			@Past LocalDateTime startDate) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}

//	public EmployeeDto(long id, @NotEmpty String name, @NotEmpty String position, @Positive int salary,
//			@Past LocalDateTime startDate, CompanyDto companyDto) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.position = position;
//		this.salary = salary;
//		this.startDate = startDate;
//		this.companyDto = companyDto;
//	}

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

//	public CompanyDto getCompanyDto() {
//		return companyDto;
//	}
//
//	public void setCompanyDto(CompanyDto companyDto) {
//		this.companyDto = companyDto;
//	}

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
