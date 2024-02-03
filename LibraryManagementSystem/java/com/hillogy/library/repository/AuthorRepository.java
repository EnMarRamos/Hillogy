package com.hillogy.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillogy.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	List<Author> findByName(String name);

	List<Author> findByNameIgnoreCaseContaining(String name);
}
