package com.hillogy.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * @author Enrique Martin Ramos
 * 
 *         Clase que contiene todos los datos de los autores
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "author")
public class Author implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "authorId")
	private Long authorId;

	@NotNull
	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = { @JoinColumn(name = "authorId") }, inverseJoinColumns = {
			@JoinColumn(name = "isbn") })
	List<Book> books = new ArrayList<>();

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	/**
	 * 
	 * @param book
	 */
	public void addBook(Book book) {
		this.books.add(book);
		book.getAuthors().add(this);
	}

	// Constructor Vacio
	public Author() {
	}

	// Constructor de la clase Autor
	public Author(Long authorId, @NotNull String name, List<Book> books) {
		super();
		this.authorId = authorId;
		this.name = name;
		this.books = books;
	}
}
