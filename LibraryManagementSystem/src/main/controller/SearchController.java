package com.hillogy.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hillogy.library.dto.BookPaginatedDTO;
import com.hillogy.library.service.SearchService;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 * Clase controlador que se encarga de las busquedas
 */
@RestController
public class SearchController {
	
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Metodo con el que buscaremos libros
     * 
     * @param query
     * @param pageable
     * @return
     */
    @GetMapping("/search/books")
    public ResponseEntity<BookPaginatedDTO> search(@RequestParam(name = "q") String query, @PageableDefault(size = 20) final Pageable pageable) {
        return ResponseEntity.ok(searchService.searchBooks(query, pageable));
    }
}
