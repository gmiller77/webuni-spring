package hu.webuni.hr.greg77.service;

public class NonUniqueCompanyIdNumberException extends RuntimeException{

	public NonUniqueCompanyIdNumberException(String companyIdNumber) {
		super("Exisiting company ID number: " + companyIdNumber);
	}
}
