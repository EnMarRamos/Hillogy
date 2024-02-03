package com.hillogy.library.exception;

@SuppressWarnings("serial")
public class NoSuchBookLoanException extends RuntimeException {

	public NoSuchBookLoanException(String message) {
		super(message);
	}
}
