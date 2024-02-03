package com.hillogy.library.exception;

@SuppressWarnings("serial")
public class NoSuchBookException extends RuntimeException {

	public NoSuchBookException(String message) {
		super(message);
	}
}
