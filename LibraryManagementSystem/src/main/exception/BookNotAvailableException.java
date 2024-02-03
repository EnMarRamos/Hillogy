package com.hillogy.library.exception;

@SuppressWarnings("serial")
public class BookNotAvailableException extends RuntimeException {

	public BookNotAvailableException(String message) {
		super(message);
	}
}
