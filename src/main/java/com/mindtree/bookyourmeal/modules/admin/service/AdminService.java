package com.mindtree.bookyourmeal.modules.admin.service;

import java.util.List;

import com.mindtree.bookyourmeal.modules.admin.dto.StatisticsDto;
import com.mindtree.bookyourmeal.modules.admin.exception.AdminException;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;

public interface AdminService {
	public StatisticsDto getStatisticsCount();

	public List<Object> getTopTenMaximumOrderedFood();

	public List<Object> getTopFiverestaurant();

	public List<Object> getLeastFiverestaurant();

	public List<RestaurantInfoDto> allRestaurantStatus(int status);

	public String changeRestaurantStatus(int restaurantId, int request) throws  AdminException ;
	
	

}
