package com.ahmedwar.brm.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.repository.UserRepository;
import com.ahmedwar.brm.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) repository.findAll();
	}

	@Override
	public User save(User user) {
		return repository.save(user);

	}

	@Override
	public User update(User user) {
		return repository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(User user) {
		repository.delete(user);
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

//	@Override
//	public User findByUsername(String username) {
//		return repository.findByUsername(username);
//	}
}