package com.ahmedwar.brm.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@NotBlank
	private String password;
	private String confirmPassword;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String username;
	private boolean enabled;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<BookRent> rents;

	@OneToMany(mappedBy = "addBy", fetch = FetchType.LAZY)
	private List<Book> books;

	@ManyToOne
	Role role;

	@Override
	public String toString() {
		return "";
	}
}
