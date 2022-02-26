
package com.mindtree.bookyourmeal.modules.restaurant.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mindtree.bookyourmeal.modules.restaurant.dto.EditFoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Ingredient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Nutrient;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantException;

@Service
public interface RestaurantService {

public FoodDto addFood(FoodDto fooddto) throws RestaurantException;
	
	List<EditFoodDto> getFood(int restaurantId) throws RestaurantException;

	FoodDto updateFood(int foodid,int availibilityStatus) throws RestaurantException;

	public RestaurantDto getrestaurantbyrestaurantid(int restaurantId) throws RestaurantException;

	public List<Object> fetchRestaurantByCity(String city);

	public RestaurantInfoDto loginRestaurant(String mailId, String password) throws RestaurantException;

	public RestaurantInfoDto registerRestaurant(RestaurantDto restaurantDto) throws RestaurantException;
	
	public int calculateNutrientScore(Nutrient nutrient) throws RestaurantException ;
	
	public Nutrient calculateNutrient(Set<Ingredient> set) throws RestaurantException;
}

