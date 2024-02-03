package com.hillogy.library.repository;

import java.util.List;

import com.hillogy.library.dto.FineDTO;

public interface FineRepositoryCustom {

	List<FineDTO> findAllFinesWithSum();
}
