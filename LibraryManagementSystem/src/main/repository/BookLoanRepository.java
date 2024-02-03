package com.hillogy.library.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillogy.library.entity.Book;
import com.hillogy.library.entity.BookLoan;
import com.hillogy.library.entity.Member;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

	List<BookLoan> findAllByDateInIsNull();

	List<BookLoan> findByMember(Member member);

	List<BookLoan> findByMemberAndBook(Member member, Book book);

	Page<BookLoan> findAllByMemberAndDateInIsNull(Member member, Pageable pageable);
}
