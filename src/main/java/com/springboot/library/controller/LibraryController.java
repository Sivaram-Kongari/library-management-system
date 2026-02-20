package com.springboot.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.library.dto.BookDTO;
import com.springboot.library.dto.IssueBookRequest;
import com.springboot.library.dto.IssueHistoryDTO;
import com.springboot.library.model.Book;
import com.springboot.library.service.LibraryService;
import jakarta.validation.Valid; 

@RestController
@RequestMapping("/books")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	@PostMapping("/storeBook")
	public Book addBook(@Valid @RequestBody BookDTO bookDTO) {

		Book insert = libraryService.insertBook(bookDTO);
		return insert;
	}
	@PutMapping("/updateBook/{id}")
	public Book modifyBook(@PathVariable Long id, @RequestBody @Valid BookDTO bookDTO) {

		Book modify = libraryService.updateBook(id, bookDTO);
		return modify;
	}
	@DeleteMapping("delete/{id}")
	public void removeBookById(@PathVariable Long id) {

		libraryService.deleteBookById(id);
	}
	@GetMapping("/getById/{id}")
	public Book fetchBookById(@PathVariable Long id) {

		Book bookById = libraryService.getBookById(id); 
		return bookById;

	}	
	@PostMapping("/issue")
	public String issueBook(@RequestBody IssueBookRequest request) {

		String issueBook = libraryService.issueBook(request);
		return issueBook;
	}
	@PutMapping("/return/{id}")
	public String returnBook(@PathVariable Long id) {

		String returnBook = libraryService.returnBook(id);
		return returnBook;
	}
	@GetMapping("/issue-history")
	public List<IssueHistoryDTO> getIssueHistory() {

		return libraryService.getIssueHistory();
	}

	//Pagination & Sorting
	@GetMapping("/getBook")
	public Page<Book> fetchBook(
			@RequestParam int page, 
			@RequestParam int size, 
			@RequestParam String sortBy, 
			@RequestParam String direction) {

		Page<Book> book = libraryService.getBook(page, size, sortBy, direction);
		return book;
	}
}