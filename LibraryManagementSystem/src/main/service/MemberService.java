package com.hillogy.library.service;

import com.hillogy.library.dto.MemberDTO;
import com.hillogy.library.dto.ResponseDTO;
import com.hillogy.library.request.BookLoanRequest;
import com.hillogy.library.request.CheckInBookRequest;
import com.hillogy.library.request.CreateMemberRequest;

public interface MemberService {

	MemberDTO addMember(CreateMemberRequest createMemberRequest);

	ResponseDTO addBookLoan(BookLoanRequest bookLoanRequest);

	ResponseDTO checkInBook(CheckInBookRequest book);
}
