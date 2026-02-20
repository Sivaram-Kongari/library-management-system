package com.springboot.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.library.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
