package com.ahmedwar.brm.services;

import java.util.List;
import java.util.Optional;

import com.ahmedwar.brm.entities.Role;

public interface RoleService {

	Optional<Role> findById(Long id);

	List<Role> findAll();

	void save(Role param);

	void update(Role param);

	void deleteById(Long id);

	void delete(Role param);

	Role findByCode(String code);

}
