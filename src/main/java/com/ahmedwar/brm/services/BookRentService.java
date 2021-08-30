package com.ahmedwar.brm.services;

import java.util.List;
import java.util.Optional;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.BookRent;
import com.ahmedwar.brm.entities.User;

public interface BookRentService {

	Optional<BookRent> findById(Long id);

	List<BookRent> findAll();

	void save(BookRent param);

	void update(BookRent param);

	void delete(Long id);

	void delete(BookRent param);

	List<BookRent> findByUser(User user);

	BookRent findByBookAndStilRented(Book book, boolean stilRented);

}
