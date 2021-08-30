package com.ahmedwar.brm.controllers;

import java.security.Principal;
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

import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.services.BookService;
import com.ahmedwar.brm.services.CategoryService;
import com.ahmedwar.brm.services.UserService;

@Controller
public class CategoryController {
	@Autowired
	private UserService userRepo;
	@Autowired
	BookService booksRepo;
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

	@GetMapping("/categories")
	public String displayAllCategories(@ModelAttribute Category category, Model model) {
		model.addAttribute("categories", this.categoryRepo.findAll());
		return "categories";
	}

	@GetMapping("/category-add")
	public String displayAddCat(@ModelAttribute Category category, Model model) {
		return "add_category";
	}

	@PostMapping("/category-add")
	public String createCat(@Valid @ModelAttribute Category category, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "add_category";
		}

		categoryRepo.save(category);
		return "redirect:/categories";
	}

	@GetMapping(value = "/category-delete")
	public String deleteCat(@RequestParam("idCategory") Long idCategory, HttpServletRequest request) {
		Optional<Category> catInDb = categoryRepo.findById(idCategory);
		if (catInDb.get().getBooks().size() < 0 || catInDb.get().getBooks().size() == 0)
			categoryRepo.deleteById(idCategory);
		return "redirect:/categories";
	}

	@GetMapping(value = "/category-update")
	public String updateCat(@RequestParam("idCategory") Long idCategory, Model model) {
		Optional<Category> catInDb = categoryRepo.findById(idCategory);
		model.addAttribute("category", catInDb.get());
		return "add_category";
	}
}
