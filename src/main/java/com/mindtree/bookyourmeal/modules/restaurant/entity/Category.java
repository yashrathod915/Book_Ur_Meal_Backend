package com.mindtree.bookyourmeal.modules.restaurant.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String categoryName;
	private boolean dietaryType;
	@OneToMany(mappedBy = "foodCategory")
	@JsonIgnore
	private Set<Food> foods;
	private LocalDateTime localDateTime=LocalDateTime.now();

	public Category() {
		super();
	}

	
	public Category(int categoryId, String categoryName, boolean dietaryType, LocalDateTime localDateTime) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.dietaryType = dietaryType;
		this.localDateTime = localDateTime;
	}


	public Category(int categoryId, String categoryName, boolean dietaryType, Set<Food> foods,
			LocalDateTime localDateTime) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.dietaryType = dietaryType;
		this.foods = foods;
		this.localDateTime = LocalDateTime.now();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isDietaryType() {
		return dietaryType;
	}

	public void setDietaryType(boolean dietaryType) {
		this.dietaryType = dietaryType;
	}

	@JsonIgnore
	public Set<Food> getFoods() {
		return foods;
	}

	@JsonProperty
	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", dietaryType=" + dietaryType
				+ ", foods=" + foods + ", localDateTime=" + localDateTime + "]";
	}

}
