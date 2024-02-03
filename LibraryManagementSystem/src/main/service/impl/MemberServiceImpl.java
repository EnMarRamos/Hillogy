package com.hillogy.library.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hillogy.library.dto.MemberDTO;
import com.hillogy.library.dto.ResponseDTO;
import com.hillogy.library.entity.Book;
import com.hillogy.library.entity.BookLoan;
import com.hillogy.library.entity.Member;
import com.hillogy.library.exception.BookNotAvailableException;
import com.hillogy.library.exception.MemberExistsException;
import com.hillogy.library.exception.MemberThresholdException;
import com.hillogy.library.exception.NoSuchBookException;
import com.hillogy.library.exception.NoSuchMemberException;
import com.hillogy.library.repository.AuthorRepository;
import com.hillogy.library.repository.BookLoanRepository;
import com.hillogy.library.repository.BookRepository;
import com.hillogy.library.repository.FineRepository;
import com.hillogy.library.repository.MemberRepository;
import com.hillogy.library.request.BookLoanRequest;
import com.hillogy.library.request.CheckInBookRequest;
import com.hillogy.library.request.CreateMemberRequest;
import com.hillogy.library.service.MemberService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	final MemberRepository memberRepository;
    final BookLoanRepository bookLoanRepository;
    final FineRepository fineRepository;
    final BookRepository bookRepository;
    final AuthorRepository authorRepository;

    public MemberServiceImpl(MemberRepository memberRepository,
                               BookLoanRepository bookLoanRepository,
                               FineRepository fineRepository,
                               BookRepository bookRepository,
                               AuthorRepository authorRepository) {
        this.memberRepository = memberRepository;
        this.bookLoanRepository = bookLoanRepository;
        this.fineRepository = fineRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
    /**
     * Método para agregar nuevos miembros
     */
    public MemberDTO addMember(CreateMemberRequest createMemberRequest) {

//    	
//        if (MemberRepository.findByDni(createMemberRequest.getDni()).isPresent()) {
//            throw new MemberExistsException("El miembro existe con ese DNI " + createMemberRequest.getDni());
//        } 

        Member Member = new Member();
        Member.setName(createMemberRequest.getName());
        Member.setAddress(createMemberRequest.getAddress());
        Member.setPhone(createMemberRequest.getPhone());
        Member.setDni(createMemberRequest.getDni());

        Member persistedMember = memberRepository.save(Member);
        MemberDTO MemberDto = new MemberDTO();

        MemberDto.setName(persistedMember.getName());
        MemberDto.setUserId(persistedMember.getUserId());
        MemberDto.setAddress(persistedMember.getAddress());

        return MemberDto;
    }
    
    /**
     * Metodo para registrar libro
     */
    @Override
    public ResponseDTO checkInBook(CheckInBookRequest checkInBookRequest) {
        Optional<Book> bookOptional = bookRepository.findById(checkInBookRequest.getIsbn());

        if (bookOptional.isEmpty()) {
            throw new NoSuchBookException("El libro no existe");
        }

        Optional<Member> memberOptional = memberRepository.findByCardId(checkInBookRequest.getCardId());

        if (memberOptional.isEmpty()) {
            throw new NoSuchMemberException("El miembro no existe");
        }

        Book book = bookOptional.get();
        Member Member = memberOptional.get();
        BookLoan bookLoan = bookLoanRepository.findByMemberAndBook(Member, book).get(0);
        bookLoan.setDateIn(LocalDateTime.now());
        book.setAvailable(true);
        bookRepository.save(book);
        bookLoanRepository.save(bookLoan);

        return new ResponseDTO("Libro registrado");
    }
    
    
    /**
     * Metodo para agregar los prestamos de los libros
     */
    @Override
    public ResponseDTO addBookLoan(BookLoanRequest bookLoanRequest) {

        String isbn = bookLoanRequest.getIsbn();
        int memberId = bookLoanRequest.getMemberId();

        Optional<Member> memberOptional = memberRepository.findByCardId(memberId);

        if (memberOptional.isEmpty()) {
            throw new NoSuchMemberException("No se ha encontrado el miembro");
        }

        List<BookLoan> bookLoans = bookLoanRepository.findByMember(memberOptional.get()).stream().filter(x -> x.getDateIn() == null)
                .toList();
        if (bookLoans.size() > 2) {
            throw new MemberThresholdException("El miemembro ya ha sacado tres libros");
        }

        Optional<Book> bookOptional = bookRepository.findById(isbn);
        if (bookOptional.isEmpty() || !bookOptional.get().isAvailable()) {
            throw new BookNotAvailableException("El libro no esta disponible");
        }

        Book book = bookOptional.get();
        Member member = memberOptional.get();
        Book book1 = bookOptional.get();
        BookLoan bookLoan = new BookLoan(member, book1);

        bookLoan.setDateOut(LocalDateTime.now());
        bookLoan.setDueDate(LocalDateTime.now().minusDays(-14));
        bookLoan.setMember(member);
        book.setAvailable(false);
        bookLoanRepository.save(bookLoan);
        bookRepository.save(book);

        return new ResponseDTO("Libro prestado");
    }

}
