package com.ahmedwar.brm.tests.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.repository.BookRepository;
import com.ahmedwar.brm.repository.CategoryRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class BookRepoTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private CategoryRepository catRepo;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(entityManager).isNotNull();
		assertThat(bookRepo).isNotNull();
	}

	@Test
	void saveTest() {
		Book bookDB = bookRepo.save(getBook());
		assertNotNull(bookDB);
		assertThat(bookDB.getId()).isGreaterThan(0);
	}

	@Test
	void findByIdTest() {
		Book bookDB = bookRepo.save(getBook());
		Book bookFound = bookRepo.findById(bookDB.getId()).get();
		assertNotNull(bookFound);
		assertThat(bookFound.getId()).isGreaterThan(0);
	}

	@Test
	void gatAllTest() {
		bookRepo.save(getBook());
		List<Book> book = (List<Book>) bookRepo.findAll();
		assertNotNull(book);
		assertThat(book.size()).isGreaterThan(0);
	}

	Book getBook() {
		Book book = new Book();
		book.setId(1000L);
		book.setName("TESTNAME");
		book.setAuthor("Author");
		book.setDescription("text lorm epsum");
		Category cat = new Category();
		cat.setId(100L);
		cat.setName("testCat");
		cat = catRepo.save(cat);
		book.setCategory(cat);
		return book;
	}

	@Test
	void deleteTest() {
		Book bookDB = bookRepo.save(getBook());
		bookRepo.deleteById(bookDB.getId());
		List<Book> book = (List<Book>) bookRepo.findAll();
		assertThat(book.size()).isLessThan(1);
	}

}
