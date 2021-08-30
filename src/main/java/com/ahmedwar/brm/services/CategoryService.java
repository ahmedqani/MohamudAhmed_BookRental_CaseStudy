package com.ahmedwar.brm.services;

import java.util.List;
import java.util.Optional;

import com.ahmedwar.brm.entities.Category;

public interface CategoryService {
	Optional<Category> findById(Long id);

	List<Category> findAll();

	Category save(Category param);

	Category update(Category param);

	void deleteById(Long id);

	void delete(Category param);

}
