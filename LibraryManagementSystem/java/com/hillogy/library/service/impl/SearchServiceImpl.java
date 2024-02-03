package com.hillogy.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hillogy.library.dto.BookDTO;
import com.hillogy.library.dto.BookPaginatedDTO;
import com.hillogy.library.dto.PaginatedDTO;
import com.hillogy.library.entity.Author;
import com.hillogy.library.entity.Book;
import com.hillogy.library.entity.BookLoan;
import com.hillogy.library.entity.Member;
import com.hillogy.library.exception.NoSuchMemberException;
import com.hillogy.library.npl.Feature;
import com.hillogy.library.npl.FeatureType;
import com.hillogy.library.npl.NLPFactory;
import com.hillogy.library.npl.NLPQuery;
import com.hillogy.library.npl.NLPResource;
import com.hillogy.library.repository.AuthorRepository;
import com.hillogy.library.repository.BookLoanRepository;
import com.hillogy.library.repository.BookRepository;
import com.hillogy.library.repository.MemberRepository;
import com.hillogy.library.service.SearchService;
import com.hillogy.library.utils.MapperUtils;

import jakarta.transaction.Transactional;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 * Clase implementacion que se encarga de las busquedas
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {
	
	private static final Logger LOGGER = LogManager.getLogger(MemberServiceImpl.class);
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final MemberRepository memberRepository;
    private final BookLoanRepository bookLoanRepository;
    
    NLPQuery nlpQuery;
    
    public SearchServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, MemberRepository memberRepository, BookLoanRepository bookLoanRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
        this.bookLoanRepository = bookLoanRepository;
        nlpQuery = NLPFactory.getSource(NLPResource.STANFORD);
    }
    
    /**
     * Método para buscar libros
     */
    @Override
    public BookPaginatedDTO searchBooks(String query, Pageable pageable) {

        if (query.matches("^(\\d{13})?$")) {
            Book book = bookRepository.findById(query).get();
            List<BookDTO> books = new ArrayList<>();
            books.add(MapperUtils.mapBookToBookDTO(book, null));
            return new BookPaginatedDTO(new PaginatedDTO(1, pageable.getPageNumber(), pageable.getPageSize(), 1), books);
        }

        Feature feature = this.nlpQuery.getQuery(query);
        LOGGER.info("Feature is " + feature);

        if (feature.getFeatureType() == FeatureType.AUTHOR) {
            List<Author> authors = authorRepository.findByNameIgnoreCaseContaining(feature.getQuery());
            Page<Book> bookList = bookRepository.findAllByAuthorsIn(authors, pageable);
            return MapperUtils.mapBooksToBooksDto(bookList);
        } else {
            Page<Book> bookPage = bookRepository.findByTitleIgnoreCaseContaining(feature.getQuery(), pageable);
            return MapperUtils.mapBooksToBooksDto(bookPage);
        }
    }
    
    /**
     * Método para buscar libros por miembros
     * 
     * @param memberName
     * @param cardId
     * @param isbn
     * @param pageable
     * @return BookPaginatedDTO
     */
    @Override
    public BookPaginatedDTO searchBooksForMember(String memberName, int cardId, String isbn, Pageable pageable) {

        Optional<Member> member = memberRepository.findByCardId(cardId);
        if (member.isEmpty()) {
            throw new NoSuchMemberException("No existe ningun miembro con este ID de miembro");
        }

        // List<Book> bookList = borrower.get().getBookLoans().stream().filter(y -> y.getDateIn() == null).map(BookLoan::getBook).collect(Collectors.toList());

        Page<BookLoan> bookLoans = bookLoanRepository.findAllByMemberAndDateInIsNull(member.get(), pageable);
        return MapperUtils.mapBookLoansToBookDTO(bookLoans);
    }
}
