package com.ahmedwar.brm.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@NamedQueries({ @NamedQuery(name = "selectCat", query = "Select b from Book b where b.category = :num") })
@EqualsAndHashCode
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotBlank
	private String author;
	@ManyToOne
	@NotNull
	private Category category;

	private boolean available;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<BookRent> rents;

	@OneToOne
	private User rentedBy;

	@ManyToOne
	private User addBy;

	@Override
	public String toString() {
		return "";
	}

}
