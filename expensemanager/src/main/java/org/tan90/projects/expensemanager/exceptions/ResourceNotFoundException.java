package org.tan90.projects.expensemanager.exceptions;

public class ResourceNotFoundException  extends Exception {

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable error) {
		super(message, error);
	}
	
}
