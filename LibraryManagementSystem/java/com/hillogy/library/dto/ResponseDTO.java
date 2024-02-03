package com.hillogy.library.dto;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 * Clase DTO de las respuestas
 */
public class ResponseDTO {

	String message;

	public ResponseDTO(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}
}
