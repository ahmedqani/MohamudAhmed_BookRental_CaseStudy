package com.ahmedwar.brm.controllers;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmedwar.brm.constants.ConstantesRole;
import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.BookRent;
import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.services.BookRentService;
import com.ahmedwar.brm.services.BookService;
import com.ahmedwar.brm.services.CategoryService;
import com.ahmedwar.brm.services.UserService;

@Controller
public class BooksController {

	@Autowired
	private UserService userRepo;
	@Autowired
	BookService booksRepo;

	@Autowired
	BookRentService bookRentRepo;
	@Autowired
	UserService membersRepo;
	@Autowired
	CategoryService categoryRepo;

	private User connectedUser = null;

	@ModelAttribute
	public void putInModel(Model model, Principal p, HttpServletRequest request) {
		connectedUser = (User) request.getSession().getAttribute("connectedUser");
		if (connectedUser == null) {
			if (p != null) {
				connectedUser = userRepo.findByUsername(p.getName());
				request.getSession().setAttribute("connectedUser", connectedUser);
			}
		}

	}

	@GetMapping("/books")
	public String displayAllBooks(Model model) {
		model.addAttribute("books", this.booksRepo.findAll());
		return "books";
	}

	@GetMapping("/search")
	public String displaySearchBook(Model model) {
		Iterable<Book> books = booksRepo.findAll();
		Iterable<User> members = membersRepo.findAll();
		model.addAttribute("allBooks", books);
		return "search";
	}

	@GetMapping("/search-books")
	public String searchBook(Model model, HttpServletRequest request) {
		String searchKey = request.getParameter("word");
		List<Book> books = new ArrayList<>();
		if (!searchKey.isBlank() || searchKey != null) {
			books = booksRepo.findByDescriptionContaining(searchKey);
			books.addAll(booksRepo.findByNameContaining(searchKey));
		}

		if (searchKey.isBlank()) {
			books = booksRepo.findAll();
		}
		model.addAttribute("books", books);
		return "search";
	}

	@GetMapping("/add")
	public String displayAddBook(@ModelAttribute Book book, Model model) {
		Iterable<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "add_book";
	}

	@PostMapping("/save-book")
	public String saveBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Iterable<Category> categories = categoryRepo.findAll();
			model.addAttribute("categories", categories);
			return "add_book";
		}

		book.setAvailable(true);
		book.setAddBy(connectedUser);
		booksRepo.save(book);
		if (connectedUser.getRole().getCode().equals(ConstantesRole.ROLE_ADMIN)) {
			return "redirect:/books";
		}
		return "redirect:/library";
	}

	@GetMapping("/book-rent")
	public String displayRentBook(@RequestParam("idBook") Long idBook, Model model) {
		Optional<Book> bookInDb = booksRepo.findById(idBook);
		BookRent bookRent = new BookRent();
		bookRent.setBook(bookInDb.get());
		bookRent.setUser(connectedUser);
		bookRent.setRentDate(new Date());
		bookRent.setStilRented(true);
		bookRentRepo.save(bookRent);
		bookInDb.get().setAvailable(false);
		booksRepo.save(bookInDb.get());
		return "redirect:/library";
	}

	@GetMapping("/book-liberate")
	public String liberateBook(@RequestParam("idBook") Long idBook, Model model) {
		Optional<Book> bookInDb = booksRepo.findById(idBook);
		BookRent bookRent = bookRentRepo.findByBookAndStilRented(bookInDb.get(), true);
		bookRent.setStilRented(false);
		bookRent.setFreeDate(new Date());
		bookInDb.get().setAvailable(true);
		bookRentRepo.save(bookRent);
		booksRepo.save(bookInDb.get());
		return "redirect:/rented-books";
	}

	@GetMapping(value = "/book-delete")
	public String deleteBook(@RequestParam("idBook") Long idBook, HttpServletRequest request) {
		booksRepo.deleteById(idBook);
		if (connectedUser.getRole().getCode().equals(ConstantesRole.ROLE_ADMIN)) {
			return "redirect:/books";
		}
		return "redirect:/library";
	}

	@GetMapping(value = "/book-update")
	public String updateBook(@RequestParam("idBook") Long idBook, Model model) {
		Optional<Book> bookInDb = booksRepo.findById(idBook);
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("book", bookInDb.get());
		return "add_book";
	}

	@GetMapping(value = "/book-detail")
	public String bookDetail(@RequestParam("idBook") Long idBook, Model model, @ModelAttribute Book book,
			BindingResult result, HttpServletRequest request) throws FileNotFoundException {
		Optional<Book> bookInDb = booksRepo.findById(idBook);
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("book", bookInDb.get());
		return "book_detail";
	}

}
