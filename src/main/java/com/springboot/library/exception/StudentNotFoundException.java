package com.springboot.library.exception;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(String msg) {
		super(msg);
	}

}
