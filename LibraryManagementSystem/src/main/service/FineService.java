package com.hillogy.library.service;

import java.util.List;

import com.hillogy.library.dto.FineDTO;
import com.hillogy.library.dto.ResponseDTO;

public interface FineService {

	ResponseDTO calculateFines();

	ResponseDTO payFine(int cardId);

	List<FineDTO> getAllFines();

	List<FineDTO> getFineForCardId(int cardId);
}
