package com.hillogy.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Enrique Martin Ramos
 * 
 * Clase que contiene todos los datos de los Usuarios de la bilioteca
 */
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "userId")
	private int userId;

	@NotNull
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
    private String address;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "phone")
	private String phone;
    
	@NotNull
	@Column(name = "dni")
	private String dni;
	
	 @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<BookLoan> bookLoans = new ArrayList<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<BookLoan> getBookLoans() {
		return bookLoans;
	}

	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}

	// Constructor Vacio
	public Member() {}

	// Constructor de la clase Member
	public Member(int userId, @NotNull String name, String address, @NotNull String email, @NotNull String phone,
			@NotNull String dni, List<BookLoan> bookLoans) {
		super();
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.dni = dni;
		this.bookLoans = bookLoans;
	}
}
