package com.ahmedwar.brm.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahmedwar.brm.entities.User;
import com.ahmedwar.brm.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(userService).isNotNull();
	}

	@Test
	public void saveTest() {
		User userDB = userService.save(getUser());
		assertNotNull(userDB);
		assertThat(userDB.getId()).isGreaterThan(0);
	}

	@Test
	public void findByIdTest() {
		User userDB = userService.save(getUser());
		User userFound = userService.findById(userDB.getId()).get();
		assertNotNull(userFound);
		assertThat(userFound.getId()).isGreaterThan(0);
	}

	@Test
	public void gatAllTest() {
		userService.save(getUser());
		List<User> user = userService.findAll();
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

}
