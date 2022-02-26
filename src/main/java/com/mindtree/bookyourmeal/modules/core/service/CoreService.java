package com.mindtree.bookyourmeal.modules.core.service;

import java.util.List;
import java.util.Set;

import com.mindtree.bookyourmeal.modules.core.exception.CoreException;
import com.mindtree.bookyourmeal.modules.core.dto.FoodRestaurantSearchDto;
import com.mindtree.bookyourmeal.modules.core.dto.SearchDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;

public interface CoreService {

	public List<FoodDto>  getallfoods();
	
	public List<Object> fetchTheRecommendedFood();
	
	public List<Object> fetchRestaurantLocation();

	public FoodDto getfoodbyfoodid(int foodId);

	public List<SearchDto> searchFood(String searchKey);

	public FoodRestaurantSearchDto searchbykeyword(String searchedfood)  throws CoreException;
}
