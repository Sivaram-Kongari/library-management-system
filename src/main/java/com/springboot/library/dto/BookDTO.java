package com.springboot.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	@NotBlank(message = "Enter valid bookname")
	private String bookName;

	@NotBlank(message = "Enter valid bookAuthor")
	private String bookAuthor;

	@Positive(message = "Total copies must be greater than 0")
	private int totalCopies;	

	private int availableCopies;
}
