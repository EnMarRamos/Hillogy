package com.hillogy.library.service;

import org.springframework.data.domain.Pageable;

import com.hillogy.library.dto.BookPaginatedDTO;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 * Clase service que se encarga de las busquedas
 */
public interface SearchService {

	BookPaginatedDTO searchBooks(String query, Pageable pageable);

	BookPaginatedDTO searchBooksForMember(String MemberName, int cardId, String isbn, Pageable pageable);
}
