package com.expenseTracking.exception;

public class InvalidException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidException(String msg) {
        super(msg);
    }
}