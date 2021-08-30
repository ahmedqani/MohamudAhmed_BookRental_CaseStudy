package com.ahmedwar.brm.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.repository.CategoryRepository;
import com.ahmedwar.brm.services.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
	@Autowired
	private CategoryService catService;

	@Autowired
	private CategoryRepository catRepo;

	@Test
	public void should_return_cat() {

		Category cat = new Category();
		cat.setId(100L);
		cat.setName("Name");
		Category catDB = catService.save(getCategory());
		assertNotNull(catDB);
		assertThat(catDB.getId()).isGreaterThan(0);

	}

	@Test
	public void should_findById_Test() {
		Category catDB = catService.save(getCategory());
		Category catFound = catService.findById(catDB.getId()).get();
		assertNotNull(catFound);
		assertThat(catFound.getId()).isGreaterThan(0);
	}

	@Test
	public void should_getAll_Test() {
		catService.save(getCategory());
		List<Category> cat = catService.findAll();
		assertNotNull(cat);
		assertThat(cat.size()).isGreaterThan(0);
	}

	Category getCategory() {
		Category cat = new Category();
		cat.setId(1000L);
		cat.setName("TESTNAME");
		return cat;
	}

}
