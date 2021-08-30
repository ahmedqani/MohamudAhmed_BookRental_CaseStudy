package com.ahmedwar.brm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.Category;

public interface BookRepository extends CrudRepository<Book, Long> {

	@Query("SELECT b from Book b where b.category = ?1")
	List<Book> findBooksByBookCategory(Optional<Category> category);

	List<Book> findByNameContaining(String str);

	List<Book> findByDescriptionContaining(String str);
}
