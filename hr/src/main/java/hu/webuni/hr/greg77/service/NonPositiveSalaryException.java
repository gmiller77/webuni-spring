package hu.webuni.hr.greg77.service;

public class NonPositiveSalaryException extends RuntimeException{

	public NonPositiveSalaryException(int salary) {
		super("Not positive salary: " + salary);
	}

}
