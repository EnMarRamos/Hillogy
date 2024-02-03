package com.hillogy.library.exception;

@SuppressWarnings("serial")
public class NoSuchMemberException extends RuntimeException {

	public NoSuchMemberException(String message) {
		super(message);
	}
}
