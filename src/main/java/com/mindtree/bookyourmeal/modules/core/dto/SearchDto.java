package com.mindtree.bookyourmeal.modules.core.dto;

public class SearchDto {
	String foodId;
	String foodName;
	String foodDescription;
	String foodPrice;
	String nutrientScore;
	String foodRating;
	String foodAvailabilityStatus;
	String pictureId;
	String pictureData;
	String restaurantId;
	String restauarantName;
	String restaurantRating;
	String restaurantStatus;
	public SearchDto() {
		super();
	}
	public SearchDto(String foodId, String foodName, String foodDescription, String foodPrice, String nutrientScore,
			String foodRating, String foodAvailabilityStatus, String pictureId, String pictureData, String restaurantId,
			String restauarantName, String restaurantRating, String restaurantStatus) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodDescription = foodDescription;
		this.foodPrice = foodPrice;
		this.nutrientScore = nutrientScore;
		this.foodRating = foodRating;
		this.foodAvailabilityStatus = foodAvailabilityStatus;
		this.pictureId = pictureId;
		this.pictureData = pictureData;
		this.restaurantId = restaurantId;
		this.restauarantName = restauarantName;
		this.restaurantRating = restaurantRating;
		this.restaurantStatus = restaurantStatus;
	}
	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
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
	public String getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(String foodPrice) {
		this.foodPrice = foodPrice;
	}
	public String getNutrientScore() {
		return nutrientScore;
	}
	public void setNutrientScore(String nutrientScore) {
		this.nutrientScore = nutrientScore;
	}
	public String getFoodRating() {
		return foodRating;
	}
	public void setFoodRating(String foodRating) {
		this.foodRating = foodRating;
	}
	public String getFoodAvailabilityStatus() {
		return foodAvailabilityStatus;
	}
	public void setFoodAvailabilityStatus(String foodAvailabilityStatus) {
		this.foodAvailabilityStatus = foodAvailabilityStatus;
	}
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	public String getPictureData() {
		return pictureData;
	}
	public void setPictureData(String pictureData) {
		this.pictureData = pictureData;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestauarantName() {
		return restauarantName;
	}
	public void setRestauarantName(String restauarantName) {
		this.restauarantName = restauarantName;
	}
	public String getRestaurantRating() {
		return restaurantRating;
	}
	public void setRestaurantRating(String restaurantRating) {
		this.restaurantRating = restaurantRating;
	}
	public String getRestaurantStatus() {
		return restaurantStatus;
	}
	public void setRestaurantStatus(String restaurantStatus) {
		this.restaurantStatus = restaurantStatus;
	}
	@Override
	public String toString() {
		return "SearchDto [foodId=" + foodId + ", foodName=" + foodName + ", foodDescription=" + foodDescription
				+ ", foodPrice=" + foodPrice + ", nutrientScore=" + nutrientScore + ", foodRating=" + foodRating
				+ ", foodAvailabilityStatus=" + foodAvailabilityStatus + ", pictureId=" + pictureId + ", pictureData="
				+ pictureData + ", restaurantId=" + restaurantId + ", restauarantName=" + restauarantName
				+ ", restaurantRating=" + restaurantRating + ", restaurantStatus=" + restaurantStatus + "]";
	}
	
}
