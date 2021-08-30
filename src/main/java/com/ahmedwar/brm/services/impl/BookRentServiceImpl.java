package com.ahmedwar.brm.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.BookRent;
import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.repository.BookRentRepository;
import com.ahmedwar.brm.services.BookRentService;

@Service
public class BookRentServiceImpl implements BookRentService {

	@Autowired
	private BookRentRepository repository;

	@Override
	public Optional<BookRent> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<BookRent> findAll() {
		return (List<BookRent>) repository.findAll();
	}

	@Override
	public void save(BookRent user) {
		repository.save(user);

	}

	@Override
	public void update(BookRent user) {
		repository.save(user);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(BookRent user) {
		repository.delete(user);
	}

	@Override
	public List<BookRent> findByUser(User user) {
		return repository.findByUser(user);
	}

	@Override
	public BookRent findByBookAndStilRented(Book book, boolean stilRented) {
		return repository.findByBookAndStilRented(book, stilRented);
	}

}
