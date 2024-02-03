package com.hillogy.library.dto;

import java.util.List;

/**
 * 
 * @author Enrique Martin Ramos
 * 
 * Clase DTO de libro
 */
public class BookDTO {

	private String ISBN;

    private String title;

    private boolean available;

    private List<AuthorDTO> authorDTOS;

    private MemberDTO memberDTO;
    
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<AuthorDTO> getAuthorDTOS() {
		return authorDTOS;
	}

	public void setAuthorDTOS(List<AuthorDTO> authorDTOS) {
		this.authorDTOS = authorDTOS;
	}

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
}
