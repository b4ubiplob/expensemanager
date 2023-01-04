package org.tan90.projects.expensemanager.exceptions;

public class ResourceNotFoundException  extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable error) {
		super(message, error);
	}
	
}
