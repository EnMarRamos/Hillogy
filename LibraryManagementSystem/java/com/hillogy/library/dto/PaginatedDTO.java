package com.hillogy.library.dto;

public record PaginatedDTO(int totalPages, int page, int size, long totalCount) {
}
