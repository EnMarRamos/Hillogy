package com.hillogy.library.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hillogy.library.dto.FineDTO;
import com.hillogy.library.dto.ResponseDTO;
import com.hillogy.library.entity.BookLoan;
import com.hillogy.library.entity.Fine;
import com.hillogy.library.entity.Member;
import com.hillogy.library.exception.NoSuchBookLoanException;
import com.hillogy.library.exception.NoSuchMemberException;
import com.hillogy.library.repository.BookLoanRepository;
import com.hillogy.library.repository.FineRepository;
import com.hillogy.library.repository.MemberRepository;
import com.hillogy.library.service.FineService;

public class FineServiceImpl implements FineService {

	private final MemberRepository memberRepository;
	private final FineRepository fineRepository;
	private final BookLoanRepository bookLoanRepository;

	public FineServiceImpl(MemberRepository memberRepository, FineRepository fineRepository,
			BookLoanRepository bookLoanRepository) {
		this.memberRepository = memberRepository;
		this.fineRepository = fineRepository;
		this.bookLoanRepository = bookLoanRepository;
	}

	public List<FineDTO> getAllFines() {
		return fineRepository.findAllFinesWithSum();
	}

	public ResponseDTO calculateFines() {
		List<BookLoan> bookLoans = bookLoanRepository.findAllByDateInIsNull();
		Fine fine;
		LocalDateTime date = LocalDateTime.now();
		for (BookLoan bookLoan : bookLoans) {
			if (bookLoan.getDueDate().isBefore(date)) {

				if (bookLoan.getFine() == null) {
					fine = new Fine(bookLoan);
				} else {
					fine = bookLoan.getFine();
				}
				long daysOverDue = Duration
						.between(bookLoan.getDueDate().toLocalDate().atStartOfDay(), date.toLocalDate().atStartOfDay())
						.toDays();
				fine.setFineAmount(daysOverDue * 0.25f);
				fineRepository.save(fine);
			}
		}

		return new ResponseDTO("All fines calculated");
	}

	@Override
	public ResponseDTO payFine(int cardId) {
		Optional<Member> memberOptional = memberRepository.findByCardId(cardId);

		if (memberOptional.isEmpty()) {
			throw new NoSuchMemberException("No existe el miembro");
		}

		List<BookLoan> bookLoans = memberOptional.get().getBookLoans();
		if (bookLoans.isEmpty()) {
			throw new NoSuchBookLoanException("Este Miembro no tiene prestamos");
		}

		for (BookLoan bookLoan : bookLoans) {
			Optional<Fine> fine = fineRepository.findByBookLoan(bookLoan);
			if (fine.isPresent()) {
				fine.get().setPaid(true);
				fineRepository.save(fine.get());
			}
		}
		return new ResponseDTO("Paid");
	}

	@Override
	public List<FineDTO> getFineForCardId(int cardId) {
		Optional<Member> memberOptional = memberRepository.findByCardId(cardId);

		if (memberOptional.isEmpty()) {
			return new ArrayList<>();
		}

		Member member = memberOptional.get();
		List<BookLoan> bookLoans = member.getBookLoans();
		return bookLoans.stream().map(x -> new FineDTO(x.getMember().getUserId(), x.getFine().getFineAmount()))
				.collect(Collectors.toList());
	}
}
