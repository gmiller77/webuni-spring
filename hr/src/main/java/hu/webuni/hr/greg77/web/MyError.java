package hu.webuni.hr.greg77.web;

import java.util.List;

import org.springframework.validation.FieldError;

public class MyError {

	String message;
	private int errorCode; // ez a saját, alkalmazásszintű errorCode-unk lesz
	private List<FieldError> fielđErrors;
	
	public MyError(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public List<FieldError> getFielđErrors() {
		return fielđErrors;
	}

	public void setFielđdErrors(List<FieldError> fielđErrors) {
		this.fielđErrors = fielđErrors;
	}

}
