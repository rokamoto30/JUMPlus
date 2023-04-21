package com.cognixia.exeption;

public class InvalidException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidException(String msg) {
		// calls the Exception(String msg) constructor
		super(msg);
	}

}
