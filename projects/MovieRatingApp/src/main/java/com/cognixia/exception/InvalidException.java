package com.cognixia.exception;

public class InvalidException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidException(String msg) {
		// calls the Exception(String msg) constructor
		super(msg);
	}
}