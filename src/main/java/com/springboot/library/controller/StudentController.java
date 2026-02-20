package com.springboot.library.controller;

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
import com.springboot.library.dto.StudentDTO;
import com.springboot.library.model.Student;
import com.springboot.library.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public Student addStudent(@Valid @RequestBody StudentDTO studentDTO) {

		Student insert = studentService.insertStudent(studentDTO);
		return insert;	
	}
	@PutMapping("/change/{id}")
	public Student modifyStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO) {

		Student updateStudent = studentService.updateStudent(id, studentDTO);
		return updateStudent;
	}
	@DeleteMapping("/delete/{id}")
	public void removeStudentById(@PathVariable Long id) {

		studentService.deleteStudentById(id);
	}
	@GetMapping("/getById/{id}")
	public Student fetchStudentById(@PathVariable Long id) {

		Student studentById = studentService.getStudentById(id);
		return studentById;
	}

	//Pagination & Sorting
	@GetMapping("/getStudent")
	public Page<Student> fetchStudent(
			@RequestParam int page, 
			@RequestParam int size, 
			@RequestParam String sortBy, 
			@RequestParam String direction) {

		Page<Student> student = studentService.getStudent(page, size, sortBy, direction);
		return student;
	}
}