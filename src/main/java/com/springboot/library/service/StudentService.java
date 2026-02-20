package com.springboot.library.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.springboot.library.dto.StudentDTO;
import com.springboot.library.exception.StudentNotFoundException;
import com.springboot.library.model.Student;
import com.springboot.library.repo.StudentRepository;

@Component
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Student insertStudent(StudentDTO studentDTO) {

		Student student = modelMapper.map(studentDTO, Student.class);
		Student save = studentRepository.save(student);
		return save;
	}
	public Student updateStudent(Long id, StudentDTO studentDTO) {

		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Student not found with the given id : "+id));
		modelMapper.map(studentDTO, existingStudent);
		Student save = studentRepository.save(existingStudent);
		return save;
	}
	public void deleteStudentById(Long id) {

		studentRepository.deleteById(id);
	}
	public Student getStudentById(Long id) {

		Student byId = studentRepository.findById(id).orElseThrow(() ->
		new StudentNotFoundException("Student not found with the given id : "+id));
		return byId;
	}

	// Pagination & Sorting
	public Page<Student> getStudent(int page, int size, String sortBy, String direction) {

		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Student> all = studentRepository.findAll(pageable);
		return all;
	}
}