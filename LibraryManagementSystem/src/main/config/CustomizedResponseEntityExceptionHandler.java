package com.hillogy.library.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hillogy.library.exception.BookNotAvailableException;
import com.hillogy.library.exception.MemberExistsException;
import com.hillogy.library.exception.MemberThresholdException;
import com.hillogy.library.exception.NoSuchBookLoanException;
import com.hillogy.library.exception.NoSuchMemberException;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MemberExistsException.class)
	public final ResponseEntity<ErrorDetail> handleMemberExistsException(MemberExistsException ex, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NoSuchMemberException.class)
	public final ResponseEntity<ErrorDetail> handleNoSuchMemberException(NoSuchMemberException ex, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MemberThresholdException.class)
	public final ResponseEntity<ErrorDetail> handleMemberThresholdException(MemberThresholdException ex,
			WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BookNotAvailableException.class)
	public final ResponseEntity<ErrorDetail> handleBookNotAvailableException(BookNotAvailableException ex,
			WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NoSuchBookLoanException.class)
	public final ResponseEntity<ErrorDetail> handleNoSuchBookLoanException(NoSuchBookLoanException ex,
			WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}
}

class ErrorDetail {
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetail(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
