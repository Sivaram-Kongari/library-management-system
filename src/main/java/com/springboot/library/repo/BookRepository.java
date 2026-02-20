package com.springboot.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
