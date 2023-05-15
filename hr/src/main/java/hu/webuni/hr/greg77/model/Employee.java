package hu.webuni.hr.greg77.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
//	private String position;
	
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	private Position position;
	
	private int salary;
	private LocalDateTime startDate;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "company_id", nullable = false)
	@ManyToOne
	private Company company;
	
	@OneToMany(mappedBy = "employee")
	private List<HolidayRequest> holidayRequests;

	@ManyToOne
	private Employee manager;
	
	private String username;
	private String password;
	
	public Employee() {
	}
	
	public Employee(String name, Position position, int salary, LocalDateTime startDate) {
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}
	
	/*
	public Employee(String name, Position position, int salary, LocalDateTime startDate, String username, String password) {
//		super();
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
		this.username = username;
		this.password = passwordEncoder.encode(password);
	}
	*/

/*
	public Employee(Long id, String name, Position position, int salary, LocalDateTime startDate) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}
*/
	
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public List<HolidayRequest> getHolidayRequests() {
		return holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
		this.holidayRequests = holidayRequests;
	}

	public void addHolidayRequest(HolidayRequest holidayRequest) {
		if(this.holidayRequests == null)
			this.holidayRequests = new ArrayList<>();
		
		this.holidayRequests.add(holidayRequest);
		holidayRequest.setEmployee(this);
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
