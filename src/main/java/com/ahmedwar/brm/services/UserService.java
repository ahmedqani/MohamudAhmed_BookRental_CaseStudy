package com.ahmedwar.brm.services;

import java.util.List;
import java.util.Optional;

import com.ahmedwar.brm.entities.User;

public interface UserService {

	Optional<User> findById(Long id);

	List<User> findAll();

	User save(User user);

	User update(User user);

	void deleteById(Long id);

	void delete(User user);

	User findByUsername(String username);

	User findByEmail(String email);
}
