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

import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.repository.UserRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepoTest {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private UserRepository userRepo;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(entityManager).isNotNull();
		assertThat(userRepo).isNotNull();
	}

	@Test
	void saveTest() {
		User userDB = userRepo.save(getUser());
		assertNotNull(userDB);
		assertThat(userDB.getId()).isGreaterThan(0);
	}

	@Test
	void findByIdTest() {
		User userDB = userRepo.save(getUser());
		User userFound = userRepo.findById(userDB.getId()).get();
		assertNotNull(userFound);
		assertThat(userFound.getId()).isGreaterThan(0);
	}

	@Test
	void gatAllTest() {
		userRepo.save(getUser());
		List<User> user = (List<User>) userRepo.findAll();
		assertNotNull(user);
		assertThat(user.size()).isGreaterThan(0);
	}

	User getUser() {
		User user = new User();
		user.setId(1000L);
		user.setName("TESTNAME");
		user.setUsername("username");
		user.setEmail("fa@fa.com");
		user.setPassword("123456");
		user.setConfirmPassword("123456");
		return user;
	}

	@Test
	void deleteTest() {
		User userDB = userRepo.save(getUser());
		userRepo.deleteById(userDB.getId());
		List<User> user = (List<User>) userRepo.findAll();
		assertThat(user.size()).isLessThan(1);
	}

}
