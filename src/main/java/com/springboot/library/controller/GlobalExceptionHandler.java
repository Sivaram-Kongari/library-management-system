package com.springboot.library.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.springboot.library.dto.ErrorResponse;
import com.springboot.library.exception.BookNotFoundException;
import com.springboot.library.exception.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

		HashMap<String, String> errors = new HashMap<>();

		//		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
		//			errors.put(error.getField(), error.getDefaultMessage());
		//		}


		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		ResponseEntity<HashMap<String, String>> rs = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);  
		return rs;
	}
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException exception) {

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());

		ResponseEntity<ErrorResponse> re = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		return re;
	}
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception) {

		ErrorResponse error1 = new ErrorResponse();
		error1.setStatus(HttpStatus.NOT_FOUND.value());
		error1.setMessage(exception.getMessage());

		ResponseEntity<ErrorResponse> rs = new ResponseEntity<>(error1, HttpStatus.NOT_FOUND);
		return rs;
	}
}