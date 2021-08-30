package com.ahmedwar.brm.services;

import java.util.List;
import java.util.Optional;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.Category;

public interface BookService {

	Optional<Book> findById(Long id);

	List<Book> findAll();

	Book save(Book book);

	Book update(Book book);

	void deleteById(Long id);

	void delete(Book book);

	List<Book> findBooksByBookCategory(Optional<Category> category);

	List<Book> findByNameContaining(String str);

	List<Book> findByDescriptionContaining(String str);

}
