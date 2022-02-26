package com.mindtree.bookyourmeal.modules.restaurant.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class CategoryDto {
	private int categoryId;
	private String categoryName;
	private boolean dietaryType;
	private LocalDateTime localDateTime=LocalDateTime.now();
	private Set<FoodDto> foods;

	public CategoryDto() {
		super();
	}

	public CategoryDto(int categoryId, String categoryName, boolean dietaryType, LocalDateTime localDateTime,
			Set<FoodDto> foods) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.dietaryType = dietaryType;
		this.localDateTime = LocalDateTime.now();
		this.foods = foods;
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

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public Set<FoodDto> getFoods() {
		return foods;
	}

	public void setFoods(Set<FoodDto> foods) {
		this.foods = foods;
	}

	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", categoryName=" + categoryName + ", dietaryType="
				+ dietaryType + ", localDateTime=" + localDateTime + ", foods=" + foods + "]";
	}

}
