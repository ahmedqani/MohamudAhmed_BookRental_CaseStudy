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

import com.ahmedwar.brm.constants.ConstantesRole;
import com.ahmedwar.brm.entities.Role;
import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.services.RoleService;
import com.ahmedwar.brm.services.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userRepo;
	@Autowired
	private RoleService roleRepo;
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

	@GetMapping("signin")
	public String signIn() {

		return "signin";
	}

	@GetMapping("signup")
	public String signUp(@ModelAttribute("user") User user) {

		return "signup";
	}

	@PostMapping("/register")
	public String createAccount(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "signup";
		}

		User userDb = userRepo.findByUsername(user.getUsername());
		if (userDb != null) {
			result.addError(
					new FieldError("user", "username", "This username is already took, please try another username "));
			model.addAttribute("usernameTook", true);
			return "signup";
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			result.addError(
					new FieldError("user", "confirmPassword", "Please enter a password identical to the previous one"));
			return "signup";
		}
		user.setPassword(encoder.encode(user.getPassword()));
		Role role = roleRepo.findByCode(ConstantesRole.ROLE_USER);
		user.setRole(role);
		user.setEnabled(true);
		user.setConfirmPassword(null);
		userRepo.save(user);
		model.addAttribute("accountCreated", true);
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "signin";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/signin";
	}
}
