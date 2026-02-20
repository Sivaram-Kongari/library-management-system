package com.springboot.library.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IssueHistoryDTO {

	private String bookName;
	private String name;
	private LocalDate issueDate;
	private LocalDate returnDate;
	private String status;
}
