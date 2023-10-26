package com.richcode.repository;

import com.richcode.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookSearchCriteriaRepository {
}
