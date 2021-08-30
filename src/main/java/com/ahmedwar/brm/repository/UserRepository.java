package com.ahmedwar.brm.repository;

import org.springframework.data.repository.CrudRepository;

import com.ahmedwar.brm.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);
}
