package com.cognixia.exception;

public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		// calls the Exception(String msg) constructor
		super(msg);
	}
}