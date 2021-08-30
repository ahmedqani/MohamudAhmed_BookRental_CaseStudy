package com.ahmedwar.brm.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.repository.BookRepository;
import com.ahmedwar.brm.repository.CategoryRepository;
import com.ahmedwar.brm.services.BookService;
import com.ahmedwar.brm.services.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
	@Autowired
	private BookService bookService;
	@Autowired
	private BookRepository bookrepo;
	@Autowired
	private CategoryService catService;
	@Autowired
	private CategoryRepository catRepo;

	@Test
	public void saveTest() {
		Book bookDB = bookService.save(getBook());
		assertNotNull(bookDB);
		assertThat(bookDB.getId()).isGreaterThan(0);
	}

	@Test
	public void findByIdTest() {
		Book bookDB = bookService.save(getBook());
		Book bookFound = bookService.findById(bookDB.getId()).get();
		assertNotNull(bookFound);
		assertThat(bookFound.getId()).isGreaterThan(0);
	}

	@Test
	public void gatAllTest() {
		bookService.save(getBook());
		List<Book> book = bookService.findAll();
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
		cat = catService.save(cat);
		book.setCategory(cat);
		return book;
	}

}
