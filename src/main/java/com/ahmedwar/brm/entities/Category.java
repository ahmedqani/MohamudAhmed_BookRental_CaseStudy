package com.ahmedwar.brm.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@ToString
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String name;
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Book> books;

	@Override
	public String toString() {
		return "";
	}
//	public Category() {
//	}
//
//	public Category(String catName) {
//		this.catName = catName;
//	}
//
//	public Category(String catName, List<Book> booksInCat) {
//		this.catName = catName;
//		this.booksInCat = booksInCat;
//	}
//
//	public long getCatId() {
//		return id;
//	}
//
//	public void setCatId(Long catId) {
//		this.id = catId;
//	}
//
//	public String getCatName() {
//		return catName;
//	}
//
//	public void setCatName(String catName) {
//		this.catName = catName;
//	}
//
//	public List<Book> getBooksInCat() {
//		return booksInCat;
//	}
//
//	public void setBooksInCat(List<Book> booksInCat) {
//		this.booksInCat = booksInCat;
//	}
//
//	public void addBooks(Book books) {
//		if (booksInCat == null) {
//			booksInCat = new ArrayList<>();
//		}
//		booksInCat.add(books);
//	}
//
//	@Override
//	public String toString() {
//		return catName;
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//
//		Category category = (Category) o;
//
//		return id == category.id;
//	}
//
//	@Override
//	public int hashCode() {
//		return (int) (id ^ (id >>> 32));
//	}
}
