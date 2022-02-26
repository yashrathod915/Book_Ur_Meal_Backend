package com.mindtree.bookyourmeal.modules.user.dto;

import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;

public class FoodQuantityDto {
	private int foodQuantityId;
	private FoodDto foodDto;
	private int foodQuantity;

	public FoodQuantityDto() {
		super();
	}

	public FoodQuantityDto(int foodQuantityId, FoodDto foodDto, int foodQuantity) {
		super();
		this.foodQuantityId = foodQuantityId;
		this.foodDto = foodDto;
		this.foodQuantity = foodQuantity;
	}

	public int getFoodQuantityId() {
		return foodQuantityId;
	}

	public void setFoodQuantityId(int foodQuantityId) {
		this.foodQuantityId = foodQuantityId;
	}

	public FoodDto getFoodDto() {
		return foodDto;
	}

	public void setFoodDto(FoodDto foodDto) {
		this.foodDto = foodDto;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	@Override
	public String toString() {
		return "FoodQuantityDto [foodQuantityId=" + foodQuantityId + ", foodDto=" + foodDto + ", foodQuantity="
				+ foodQuantity + "]";
	}

}
