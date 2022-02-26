package com.mindtree.bookyourmeal.modules.core.dto;

import java.util.List;

import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;

public class FoodRestaurantSearchDto {
	
	private List<FoodDto> foods;
	private List<RestaurantWithoutFoodsDto> restaurants;
	public FoodRestaurantSearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FoodRestaurantSearchDto(List<FoodDto> foods, List<RestaurantWithoutFoodsDto> restaurants) {
		super();
		this.foods = foods;
		this.restaurants = restaurants;
	}
	public List<FoodDto> getFoods() {
		return foods;
	}
	public void setFoods(List<FoodDto> foods) {
		this.foods = foods;
	}
	public List<RestaurantWithoutFoodsDto> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<RestaurantWithoutFoodsDto> restaurants) {
		this.restaurants = restaurants;
	}
	@Override
	public String toString() {
		return "FoodRestaurantSearchDto [foods=" + foods + ", restaurants=" + restaurants + "]";
	}
	
	

}
