package com.ahmedwar.brm.controllers;

import java.security.Principal;

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

import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.services.BookService;
import com.ahmedwar.brm.services.UserService;

@Controller
public class AccountController {
	@Autowired
	private UserService userRepo;
	@Autowired
	BookService bookRepo;
	@Autowired
	private PasswordEncoder encoder;

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

	@GetMapping("/account")
	public String displayAccount(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		model.addAttribute("account", this.connectedUser);
		return "account";
	}

	@GetMapping("/change-password")
	public String changePassword(@ModelAttribute User user, Model model) {
		model.addAttribute("books", bookRepo.findAll());
		model.addAttribute("account", this.connectedUser);
		return "change_pass";
	}

	@PostMapping("/change-password")
	public String storePassword(@Valid @ModelAttribute User user, BindingResult result, Model model) {

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			result.addError(
					new FieldError("user", "confirmPassword", "Please enter a password identical to the previous one"));
			return "change_pass";
		}
		this.connectedUser.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(connectedUser);
		return "redirect:/account";
	}
}
