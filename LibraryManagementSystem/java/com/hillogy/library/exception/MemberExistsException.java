package com.hillogy.library.exception;

@SuppressWarnings("serial")
public class MemberExistsException extends RuntimeException {

	public MemberExistsException(String message) {
		super(message);
	}
}
