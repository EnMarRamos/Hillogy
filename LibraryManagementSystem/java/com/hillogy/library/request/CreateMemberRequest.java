package com.hillogy.library.request;

import jakarta.validation.constraints.NotNull;

public class CreateMemberRequest {

	@NotNull(message = "El nombre del miembro es obligatorio")
	private String name;

	@NotNull(message = "La direccion del miembro es obligatorio")
	private String address;

	@NotNull(message = "El telefono del miembro es obligatorio")
	private String phone;

	@NotNull(message = "El correo electronico del miembro es obligatorio")
	private String email;

	@NotNull(message = "El DNI del miembro es obligatorio")
	private String dni;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
