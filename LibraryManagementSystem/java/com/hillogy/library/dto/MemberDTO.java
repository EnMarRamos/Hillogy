package com.hillogy.library.dto;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 * Clase DTO de miembro
 */
public class MemberDTO {

	private String name;
	private int userId;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
