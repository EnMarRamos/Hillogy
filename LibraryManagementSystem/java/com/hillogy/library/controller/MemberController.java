package com.hillogy.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hillogy.library.request.BookLoanRequest;
import com.hillogy.library.request.CheckInBookRequest;
import com.hillogy.library.request.CreateMemberRequest;
import com.hillogy.library.service.MemberService;

import jakarta.validation.Valid;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 * Clase controlador de los miembros de la biblioteca
 */
@RestController
public class MemberController {

	final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping(value = "/member")
	public ResponseEntity<?> addMember(@Valid @RequestBody CreateMemberRequest createMemberRequest) {
		return ResponseEntity.ok(memberService.addMember(createMemberRequest));
	}

	@PostMapping(value = "/member/checkout")
	public ResponseEntity<?> checkoutBook(@Valid @RequestBody BookLoanRequest bookLoanRequest) {
		return ResponseEntity.ok(memberService.addBookLoan(bookLoanRequest));
	}

	@PostMapping(value = "/member/checkIn")
	public ResponseEntity<?> checkInBook(@Valid @RequestBody CheckInBookRequest book) {
		return ResponseEntity.ok(memberService.checkInBook(book));
	}
}
