package com.mindtree.bookyourmeal.modules.restaurant.dto;

public class EditFoodDto {
	private int foodId;
	private String foodName;
	private boolean foodAvailabilityStatus;
	
	
	public EditFoodDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EditFoodDto(int foodId, String foodName, boolean foodAvailabilityStatus) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodAvailabilityStatus = foodAvailabilityStatus;
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


	public boolean isFoodAvailabilityStatus() {
		return foodAvailabilityStatus;
	}


	public void setFoodAvailabilityStatus(boolean foodAvailabilityStatus) {
		this.foodAvailabilityStatus = foodAvailabilityStatus;
	}


	@Override
	public String toString() {
		return "EditFoodDto [foodId=" + foodId + ", foodName=" + foodName + ", foodAvailabilityStatus="
				+ foodAvailabilityStatus + "]";
	}
	
	
}
