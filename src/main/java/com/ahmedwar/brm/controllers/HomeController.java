package com.ahmedwar.brm.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.services.BookService;
import com.ahmedwar.brm.services.CategoryService;
import com.ahmedwar.brm.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private BookService bookRepo;
	@Autowired
	private UserService memberRepo;
	@Autowired
	private CategoryService categoryRepo;
	@Autowired
	private UserService userRepo;

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

	@GetMapping(value = { "/", "home" })
	public String displayHome(Model model) {
		List<Category> categories = (List<Category>) categoryRepo.findAll();
//		List<Book> booksByBookCategory1 = booksRepo.findBooksByBookCategory(categoryRepo.findById(1L));
//		List<Book> booksByBookCategory2 = booksRepo.findBooksByBookCategory(categoryRepo.findById(2L));
//		int csSize = booksByBookCategory1.size();
//		int mtSize = booksByBookCategory2.size();
//		System.out.println(booksByBookCategory1);
//		System.out.println(booksByBookCategory2);
//		System.out.println(csSize);
//		System.out.println(mtSize);
		model.addAttribute("aCSSize", 10);
		model.addAttribute("aMtSize", 22);
		model.addAttribute("categories", categories);
		return "home";

	}

	@GetMapping("library")
	public String library(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		return "library";
	}

}
