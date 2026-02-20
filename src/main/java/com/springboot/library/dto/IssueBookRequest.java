package com.springboot.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueBookRequest {

	private Long bookId;
	private Long studentId;
}
