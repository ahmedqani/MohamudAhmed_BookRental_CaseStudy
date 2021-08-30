package com.ahmedwar.brm.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmedwar.brm.entities.Book;
import com.ahmedwar.brm.entities.BookRent;
import com.ahmedwar.brm.entities.Role;
import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.services.BookRentService;
import com.ahmedwar.brm.services.BookService;
import com.ahmedwar.brm.services.RoleService;
import com.ahmedwar.brm.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userRepo;
	@Autowired
	BookService booksRepo;

	@Autowired
	BookRentService bookRentRepo;
	@Autowired
	UserService membersRepo;
	private User connectedUser = null;
	@Autowired
	private RoleService roleRepo;
	@Autowired
	private PasswordEncoder encoder;

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

	@GetMapping("/users")
	public String displayAllCategories(@ModelAttribute User user, Model model) {
		model.addAttribute("users", this.userRepo.findAll());
		return "users";
	}

	@GetMapping("/user-add")
	public String displayAddCat(@ModelAttribute User user, Model model) {
		model.addAttribute("roles", roleRepo.findAll());
		return "add_user";
	}

	@PostMapping("/user-add")
	public String createCat(@Valid @ModelAttribute User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("roles", roleRepo.findAll());
			return "add_user";
		}

		if (user.getId() == null) {
			User userDb = userRepo.findByUsername(user.getUsername());
			if (userDb != null) {
				result.addError(new FieldError("user", "username",
						"This username is already took, please try another username "));
				model.addAttribute("usernameTook", true);
				model.addAttribute("roles", roleRepo.findAll());
				return "add_user";
			}
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			result.addError(
					new FieldError("user", "confirmPassword", "Please enter a password identical to the previous one"));
			model.addAttribute("roles", roleRepo.findAll());
			return "add_user";
		}
		user.setPassword(encoder.encode(user.getPassword()));
		Role role = roleRepo.findById(user.getRole().getId()).get();
		user.setConfirmPassword(null);
		user.setRole(role);
		user.setEnabled(true);

		userRepo.save(user);
		return "redirect:/users";
	}

	@GetMapping(value = "/user-delete")
	public String deleteCat(@RequestParam("idUser") Long idUser, HttpServletRequest request) {
		Optional<User> userInDb = userRepo.findById(idUser);
		if (userInDb.get().getRents().size() < 0 || userInDb.get().getRents().size() == 0)
			if (userInDb.get().getBooks().size() < 0 || userInDb.get().getBooks().size() == 0)
				userRepo.deleteById(idUser);
		return "redirect:/users";
	}

	@GetMapping(value = "/user-update")
	public String updateCat(@RequestParam("idUser") Long idUser, Model model) {
		Optional<User> userInDb = userRepo.findById(idUser);
		model.addAttribute("user", userInDb.get());
		model.addAttribute("roles", roleRepo.findAll());
		return "add_user";
	}

	@GetMapping(value = "/rented-books")
	public String rentedBook(Model model) {
		List<BookRent> rented = bookRentRepo.findByUser(connectedUser);
		List<Book> books = new ArrayList<>();
		for (BookRent book : rented) {
			if (!book.getBook().isAvailable() && book.isStilRented())
				books.add(book.getBook());
		}
		model.addAttribute("books", books);
		return "my_books";
	}
}
