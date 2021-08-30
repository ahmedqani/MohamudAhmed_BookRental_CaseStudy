package com.ahmedwar.brm.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.repository.CategoryRepository;
import com.ahmedwar.brm.services.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public Optional<Category> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>) repository.findAll();
	}

	@Override
	public Category save(Category user) {
		return repository.save(user);

	}

	@Override
	public Category update(Category user) {
		return repository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(Category user) {
		repository.delete(user);
	}

}
