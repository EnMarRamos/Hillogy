package com.hillogy.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillogy.library.entity.BookLoan;
import com.hillogy.library.entity.Fine;

@Repository
public interface FineRepository extends JpaRepository<Fine, Integer>, FineRepositoryCustom {

	Optional<Fine> findByBookLoan(BookLoan bookLoan);
}
