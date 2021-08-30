package com.ahmedwar.brm.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.repository.BookRepository;
import com.ahmedwar.brm.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Override
	public Optional<Book> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Book> findAll() {
		return (List<Book>) repository.findAll();
	}

	@Override
	public Book save(Book user) {
		return repository.save(user);

	}

	@Override
	public Book update(Book user) {
		return repository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(Book user) {
		repository.delete(user);
	}

	@Override
	public List<Book> findBooksByBookCategory(Optional<Category> category) {
		return repository.findBooksByBookCategory(category);
	}

	@Override
	public List<Book> findByNameContaining(String str) {
		return repository.findByNameContaining(str);
	}

	@Override
	public List<Book> findByDescriptionContaining(String str) {
		return repository.findByDescriptionContaining(str);
	}

}
