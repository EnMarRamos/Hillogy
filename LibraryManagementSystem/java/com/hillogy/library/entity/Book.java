package com.hillogy.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enrique Martin Ramos
 * 
 * Clase que contiene todos los datos de los libros
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "book")
public class Book implements Serializable {

	@Id
	@Column(name = "isbn")
	private String isbn;

	@NotNull
	@Column(name = "title")
	private String title;

	@Column(name = "available")
	private boolean available;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
	List<Author> authors = new ArrayList<>();
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	// Constructor Vacio
	public Book() {
	}

	// Constructor de la clase Libro
	public Book(String isbn, @NotNull String title, boolean available, List<Author> authors) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.available = available;
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book{" + 
				", isbn='" + isbn + '\'' + 
				", title='" + title + '\'' + 
				'}';
	}
}
