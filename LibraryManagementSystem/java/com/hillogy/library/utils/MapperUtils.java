package com.hillogy.library.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.hillogy.library.dto.AuthorDTO;
import com.hillogy.library.dto.BookDTO;
import com.hillogy.library.dto.BookPaginatedDTO;
import com.hillogy.library.dto.MemberDTO;
import com.hillogy.library.dto.PaginatedDTO;
import com.hillogy.library.entity.Book;
import com.hillogy.library.entity.BookLoan;
import com.hillogy.library.entity.Member;

public class MapperUtils {

	/**
	 * Mapeamos la clase libros con listado de libros DTO
	 * 
	 * @param books
	 * @return BookPaginatedDTO
	 */
	public static BookPaginatedDTO mapBooksToBooksDto(Page<Book> books) {
		List<BookDTO> bookDTOS = new ArrayList<>();
		books.getContent().forEach(bo -> {
			BookDTO bookDTO = mapBookToBookDTO(bo, null);
			bookDTOS.add(bookDTO);
		});

		PaginatedDTO paginatedDTO = new PaginatedDTO(books.getTotalPages(), books.getNumber(), books.getSize(),
				books.getTotalElements());
		return new BookPaginatedDTO(paginatedDTO, bookDTOS);
	}

	public static BookPaginatedDTO mapBookLoansToBookDTO(Page<BookLoan> bookLoans) {

		List<BookDTO> books = new ArrayList<>();
		bookLoans.getContent().forEach(bookLoan -> {
			books.add(mapBookToBookDTO(bookLoan.getBook(), bookLoan.getMember()));
		});
		PaginatedDTO paginatedDTO = new PaginatedDTO(bookLoans.getTotalPages(), bookLoans.getNumber(),
				bookLoans.getSize(), bookLoans.getTotalElements());
		return new BookPaginatedDTO(paginatedDTO, books);

	}

	/**
	 * Mapeamos la clase miembros con la miembro DTO
	 * 
	 * @param member
	 * @return MemberDTO
	 */
	public static MemberDTO mapMemberToMemberDTO(Member member) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(memberDTO.getName());
		memberDTO.setUserId(memberDTO.getUserId());
		return memberDTO;
	}

	/**
	 * 
	 * Mapeamos la clase libro con Libro DTO
	 * 
	 * @param book
	 * @param member
	 * @return BookDTO
	 */
	public static BookDTO mapBookToBookDTO(Book book, Member member) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setISBN(book.getIsbn());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAvailable(book.isAvailable());

		List<AuthorDTO> authorDTOS = new ArrayList<>();
		book.getAuthors().forEach(y -> {
			AuthorDTO authorDTO1 = new AuthorDTO();
			authorDTO1.setName(y.getName());
			authorDTOS.add(authorDTO1);
		});

		bookDTO.setAuthorDTOS(authorDTOS);

		if (member != null) {
			bookDTO.setMemberDTO(mapMemberToMemberDTO(member));
		}
		return bookDTO;
	}
}
