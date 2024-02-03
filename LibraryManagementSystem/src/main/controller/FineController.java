package com.hillogy.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hillogy.library.request.PayFineRequest;
import com.hillogy.library.service.FineService;

@RestController
public class FineController {

	private final FineService libraryService;

	public FineController(FineService libraryService) {
		this.libraryService = libraryService;
	}

	@PostMapping(value = "/fine/calculate")
	public ResponseEntity<?> addOrUpdateFine() {
		return ResponseEntity.ok(libraryService.calculateFines());
	}

	@GetMapping(value = "/fines")
	public ResponseEntity<?> getAllFines() {
		return ResponseEntity.ok(libraryService.getAllFines());
	}

	@PostMapping(value = "/fine")
	public ResponseEntity<?> payFine(@RequestBody PayFineRequest payFineRequest) {
		return ResponseEntity.ok(libraryService.payFine(payFineRequest.getCardId()));
	}

	@GetMapping(value = "/fine")
	public ResponseEntity<?> getFineForCardId(@RequestParam("cardId") int cardId) {
		return ResponseEntity.ok(libraryService.getFineForCardId(cardId));
	}
}
