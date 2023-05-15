package hu.webuni.hr.greg77.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import hu.webuni.hr.greg77.model.Employee;

public class HrUser extends User {
	
	public HrUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Employee employee) {
		super(username, password, authorities);
		this.employee = employee;
	}

	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
