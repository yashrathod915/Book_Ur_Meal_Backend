package com.mindtree.bookyourmeal.modules.restaurant.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.core.dto.ImageDto;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FoodDto implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int foodId;
	private String foodName;
	private String foodDescription;
	private int foodPrice;
	private int foodQuantity;
	private float foodRating;
	private NutrientDto nutrient;
	private int nutrientScore;
	private CategoryDto foodCategory;
	private RestaurantDto restaurant;
	private ImageDto foodImage;
	private boolean foodAvailabilityStatus;
	private Set<IngredientDto> ingredients;
	private LocalDateTime localDateTime=LocalDateTime.now();

	public FoodDto() {
		super();
	}

	public FoodDto(int foodId, String foodName, String foodDescription, int foodPrice, int foodQuantity,
			float foodRating, NutrientDto nutrient, int nutrientScore, CategoryDto foodCategory,
			RestaurantDto restaurant, ImageDto foodImage, boolean foodAvailabilityStatus,
			Set<IngredientDto> ingredients, LocalDateTime localDateTime) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodDescription = foodDescription;
		this.foodPrice = foodPrice;
		this.foodQuantity = foodQuantity;
		this.foodRating = foodRating;
		this.nutrient = nutrient;
		this.nutrientScore = nutrientScore;
		this.foodCategory = foodCategory;
		this.restaurant = restaurant;
		this.foodImage = foodImage;
		this.foodAvailabilityStatus = foodAvailabilityStatus;
		this.ingredients = ingredients;
		this.localDateTime = localDateTime;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodDescription() {
		return foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	public float getFoodRating() {
		return foodRating;
	}

	public void setFoodRating(float foodRating) {
		this.foodRating = foodRating;
	}

	public NutrientDto getNutrient() {
		return nutrient;
	}

	public void setNutrient(NutrientDto nutrient) {
		this.nutrient = nutrient;
	}

	public int getNutrientScore() {
		return nutrientScore;
	}

	public void setNutrientScore(int nutrientScore) {
		this.nutrientScore = nutrientScore;
	}

	public CategoryDto getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(CategoryDto foodCategory) {
		this.foodCategory = foodCategory;
	}

	public RestaurantDto getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDto restaurant) {
		this.restaurant = restaurant;
	}

	public ImageDto getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(ImageDto foodImage) {
		this.foodImage = foodImage;
	}

	public boolean isFoodAvailabilityStatus() {
		return foodAvailabilityStatus;
	}

	public void setFoodAvailabilityStatus(boolean foodAvailabilityStatus) {
		this.foodAvailabilityStatus = foodAvailabilityStatus;
	}

	public Set<IngredientDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientDto> ingredients) {
		this.ingredients = ingredients;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FoodDto [foodId=" + foodId + ", foodName=" + foodName + ", foodDescription=" + foodDescription
				+ ", foodPrice=" + foodPrice + ", foodQuantity=" + foodQuantity + ", foodRating=" + foodRating
				+ ", nutrient=" + nutrient + ", nutrientScore=" + nutrientScore + ", foodCategory=" + foodCategory
				+ ", restaurant=" + restaurant + ", foodImage=" + foodImage + ", foodAvailabilityStatus="
				+ foodAvailabilityStatus + ", ingredients=" + ingredients + ", localDateTime=" + localDateTime + "]";
	}


}
