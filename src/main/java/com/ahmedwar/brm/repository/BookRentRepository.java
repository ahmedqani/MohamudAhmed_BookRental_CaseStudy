package com.ahmedwar.brm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.BookRent;
import com.ahmedwar.brm.entities.User;

public interface BookRentRepository extends CrudRepository<BookRent, Long> {

	List<BookRent> findByUser(User user);

	BookRent findByBookAndStilRented(Book book, boolean stilRented);

}
