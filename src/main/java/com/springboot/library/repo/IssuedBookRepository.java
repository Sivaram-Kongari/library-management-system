package com.springboot.library.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.library.dto.IssueHistoryDTO;
import com.springboot.library.model.IssuedBook;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

	@Query("""
			   SELECT new com.springboot.library.dto.IssueHistoryDTO(
			       b.bookName,
			       s.name,
			       i.issueDate,
			       i.returnDate,
			       i.status
			   )
			   FROM IssuedBook i
			   JOIN i.book b
			   JOIN i.student s
			""")
	List<IssueHistoryDTO> getIssueHistory();
}
