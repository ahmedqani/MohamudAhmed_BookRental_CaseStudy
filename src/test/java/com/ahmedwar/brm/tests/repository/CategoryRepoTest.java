package com.ahmedwar.brm.tests.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ahmedwar.brm.entities.Category;
import com.ahmedwar.brm.repository.CategoryRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CategoryRepoTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CategoryRepository catRepo;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(entityManager).isNotNull();
		assertThat(catRepo).isNotNull();
	}

	@Test
	void saveTest() {
		Category catDB = catRepo.save(getCategory());
		assertNotNull(catDB);
		assertThat(catDB.getId()).isGreaterThan(0);
	}

	@Test
	void findByIdTest() {
		Category catDB = catRepo.save(getCategory());
		Category catFound = catRepo.findById(catDB.getId()).get();
		assertNotNull(catFound);
		assertThat(catFound.getId()).isGreaterThan(0);
	}

	@Test
	void gatAllTest() {
		catRepo.save(getCategory());
		List<Category> cat = (List<Category>) catRepo.findAll();
		assertNotNull(cat);
		assertThat(cat.size()).isGreaterThan(0);
	}

	Category getCategory() {
		Category cat = new Category();
		cat.setId(1000L);
		cat.setName("TESTNAME");
		return cat;
	}

	@Test
	void deleteTest() {
		Category catDB = catRepo.save(getCategory());
		catRepo.deleteById(catDB.getId());
		List<Category> cat = (List<Category>) catRepo.findAll();
		assertThat(cat.size()).isLessThan(1);
	}

}
