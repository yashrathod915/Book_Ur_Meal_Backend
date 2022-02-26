package com.mindtree.bookyourmeal.modules.admin.service.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bookyourmeal.modules.admin.dto.StatisticsDto;
import com.mindtree.bookyourmeal.modules.admin.exception.AdminException;
import com.mindtree.bookyourmeal.modules.admin.service.AdminService;
import com.mindtree.bookyourmeal.modules.core.repository.UserRepository;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.restaurant.exception.ErrorConstants;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.RestaurantRepository;
import com.mindtree.bookyourmeal.modules.user.repository.SuborderRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	RestaurantRepository restuarantRepo;
	@Autowired
	FoodRepository foodRepo;
	@Autowired
	SuborderRepository suborderRepo;

	ModelMapper modelMapper = new ModelMapper();

	private RestaurantInfoDto convertRestaurantInfoEntityToDto(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantInfoDto.class);
	}

	@Override
	public StatisticsDto getStatisticsCount() {
		int totalUser = userRepo.getTotalUser();
		int totalRestaurant = restuarantRepo.getAllRestaurant();
		int totalApprovedRestaurant = restuarantRepo.getApprovedRestaurant();
		int totalRejectedRestaurant = restuarantRepo.getRejectedRestaurant();
		int toalPendingRestaurant = restuarantRepo.getPendingApprovalRestaurant();
		int orders = restuarantRepo.getFoodOrders();
		StatisticsDto statisticsDto = new StatisticsDto();
		statisticsDto.setTotalCustomers(totalUser);
		statisticsDto.setTotalRestaurant(totalRestaurant);
		statisticsDto.setApprovedRestaurant(totalApprovedRestaurant);
		statisticsDto.setRejectedRestaurant(totalRejectedRestaurant);
		statisticsDto.setPendingRestaurant(toalPendingRestaurant);
		statisticsDto.setOrders(orders);
		System.out.println(statisticsDto.toString());
		return statisticsDto;
	}

	public List<Object> getTopTenMaximumOrderedFood() {
		return suborderRepo.getMaximumTenOrderFood();
	}

	@Override
	public List<Object> getTopFiverestaurant() {
		return suborderRepo.getTopFiveRestaurant();
	}

	@Override
	public List<Object> getLeastFiverestaurant() {
		return suborderRepo.getLeastFiveRestaurant();
	}

	public List<RestaurantInfoDto> allRestaurantStatus(int status) {
		List<Restaurant> restaurants = restuarantRepo.findAll();
		restaurants = restaurants.stream().filter(r -> (r.getRestaurantStatus() == status))
				.collect(Collectors.toList());
		return restaurants.stream().map(r -> convertRestaurantInfoEntityToDto(r)).collect(Collectors.toList());
	}

	public String changeRestaurantStatus(int restaurantId, int request) throws AdminException {
		Optional<Restaurant> restaurantOptional = restuarantRepo.findById(restaurantId);
		restaurantOptional.orElseThrow(() -> new AdminException(ErrorConstants.NOSUCHRESTAURANT));
		Restaurant restaurant = restaurantOptional.get();
		int currentStatus = restaurant.getRestaurantStatus();
		Set<Food> foodList = restaurant.getFoods();
		Set<Food> updatedList = new HashSet<Food>();
		if (request == 1 && (currentStatus == 0 || currentStatus == -1)) {
			restaurant.setRestaurantStatus(1);
			for (Food f : foodList) {
				f.setFoodAvailabilityStatus(true);
				updatedList.add(f);
			}
			foodRepo.saveAll(updatedList);
			restuarantRepo.save(restaurant);
			return "Restaurant Approved";
		} else if (request == 0 && (currentStatus == 0 || currentStatus == 1)) {
			restaurant.setRestaurantStatus(-1);
			for (Food f : foodList) {
				f.setFoodAvailabilityStatus(false);
				updatedList.add(f);
			}
			foodRepo.saveAll(updatedList);
			restuarantRepo.save(restaurant);
			return "Restaurant Rejected";
		}
		throw new AdminException("Inavlid Input Request");
	}

}
