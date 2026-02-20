package com.springboot.library.service;

import java.time.LocalDate;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.springboot.library.dto.BookDTO;
import com.springboot.library.dto.IssueBookRequest;
import com.springboot.library.dto.IssueHistoryDTO;
import com.springboot.library.exception.BookNotFoundException;
import com.springboot.library.exception.StudentNotFoundException;
import com.springboot.library.model.Book;
import com.springboot.library.model.IssuedBook;
import com.springboot.library.model.Student;
import com.springboot.library.repo.BookRepository;
import com.springboot.library.repo.IssuedBookRepository;
import com.springboot.library.repo.StudentRepository;
import jakarta.transaction.Transactional;

@Component
public class LibraryService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private IssuedBookRepository issuedBookRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Book insertBook(BookDTO bookDTO) {

		Book b1 = modelMapper.map(bookDTO, Book.class);
		Book save = bookRepository.save(b1);
		return save;
	}
	public Book updateBook(Long id, BookDTO bookDTO) {

		Book existingBook = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book is not available with the given id : "+id));
		modelMapper.map(bookDTO, existingBook);
		Book save = bookRepository.save(existingBook);
		return save;
	}
	public void deleteBookById(Long id) {

		bookRepository.deleteById(id);
	}
	public Book getBookById(Long id) {

		Book byId = bookRepository.findById(id).orElseThrow(()-> 
		new BookNotFoundException("Book not found with given id : "+id));
		return byId;
	}
	@Transactional
	public String issueBook(IssueBookRequest request) {

		Book book = bookRepository.findById(request.getBookId()).orElseThrow(()-> 
		new BookNotFoundException("Book not found"));

		Student student = studentRepository.findById(request.getStudentId()).orElseThrow(()->
		new StudentNotFoundException("Student not found"));

		if(book.getAvailableCopies()<=0) {

			throw new BookNotFoundException("Book not available");
		}
		//  reduce available copies
		book.setAvailableCopies(book.getAvailableCopies()-1);
		bookRepository.save(book);

		// insert into issued_book table
		IssuedBook issuedBook = new IssuedBook();
		issuedBook.setBook(book);
		issuedBook.setStudent(student);
		issuedBook.setIssueDate(LocalDate.now());
		issuedBook.setStatus("ISSUED");

		issuedBookRepository.save(issuedBook);

		return "Book issued successfully";
	}
	@Transactional
	public String returnBook(Long id) {

		IssuedBook issuedBook = issuedBookRepository.findById(id).orElseThrow(()-> 
		new RuntimeException("Issue record not found"));

		if("RETURNED".equalsIgnoreCase(issuedBook.getStatus())){

			throw new RuntimeException("Book already returned");
		}
		issuedBook.setStatus("RETURNED");
		issuedBook.setReturnDate(LocalDate.now());
		issuedBookRepository.save(issuedBook);

		Book book = issuedBook.getBook();
		book.setAvailableCopies(book.getAvailableCopies()+1);
		bookRepository.save(book);

		return "Book returned successfully";
	}
	public List<IssueHistoryDTO> getIssueHistory() {

		return issuedBookRepository.getIssueHistory();
	}

	//Pagination & Sorting
	public Page<Book> getBook(int page, int size, String sortBy, String direction) {

		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Book> all = bookRepository.findAll(pageable);
		return all;
	}
}