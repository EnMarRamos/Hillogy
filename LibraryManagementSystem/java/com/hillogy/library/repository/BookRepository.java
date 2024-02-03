package com.hillogy.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillogy.library.entity.Author;
import com.hillogy.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	Optional<Book> findById(String isbn);

	Page<Book> findByTitleIgnoreCaseContaining(String title, Pageable pageable);

	Page<Book> findAllByAuthorsIn(List<Author> authors, Pageable pageable);
}
