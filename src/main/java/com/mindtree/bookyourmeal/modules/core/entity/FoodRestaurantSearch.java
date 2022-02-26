package com.mindtree.bookyourmeal.modules.core.entity;

import java.io.Serializable;
import java.util.List;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;


public class FoodRestaurantSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Food> foods;
	private List<Restaurant> restaurants;
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FoodRestaurantSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FoodRestaurantSearch(List<Food> foods, List<Restaurant> restaurants) {
		super();
		this.foods = foods;
		this.restaurants = restaurants;
	}
	@Override
	public String toString() {
		return "FoodRestaurantSearch [foods=" + foods + ", restaurants=" + restaurants + "]";
	}
	
}
