package com.hillogy.library.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "book_loan")
public class BookLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "book_loan_id")
	private Long bookLoanId;

	@ManyToOne
	@JoinColumn(name = "isbn")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "card_id")
	private Member member;

	@Column(name = "date_out")
	private LocalDateTime dateOut;

	@Column(name = "date_in")
	private LocalDateTime dateIn;

	@Column(name = "due_date")
	private LocalDateTime dueDate;

	@OneToOne(mappedBy = "bookLoan")
	private Fine fine;

	public Long getBookLoanId() {
		return bookLoanId;
	}

	public void setBookLoanId(Long bookLoanId) {
		this.bookLoanId = bookLoanId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDateTime getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDateTime dateOut) {
		this.dateOut = dateOut;
	}

	public LocalDateTime getDateIn() {
		return dateIn;
	}

	public void setDateIn(LocalDateTime dateIn) {
		this.dateIn = dateIn;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Fine getFine() {
		return fine;
	}

	public void setFine(Fine fine) {
		this.fine = fine;
	}

	public BookLoan() {
	}
	
    public BookLoan(Member member, Book book) {
        this.member = member;
        this.book = book;
    }
}
