package com.springboot.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

	@NotBlank(message = "Name should not be empty")
	private String name;

	@Email(message = "Invalid email")
	@NotBlank(message = "Email should not be empty")
	private String email;
}
