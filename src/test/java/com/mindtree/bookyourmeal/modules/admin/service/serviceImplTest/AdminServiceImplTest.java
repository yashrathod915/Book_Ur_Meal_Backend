package com.mindtree.bookyourmeal.modules.admin.service.serviceImplTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.bookyourmeal.modules.admin.dto.StatisticsDto;
import com.mindtree.bookyourmeal.modules.admin.exception.AdminException;
import com.mindtree.bookyourmeal.modules.admin.service.AdminService;
import com.mindtree.bookyourmeal.modules.admin.service.serviceImpl.AdminServiceImpl;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.repository.UserRepository;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantException;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.RestaurantRepository;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;
import com.mindtree.bookyourmeal.modules.user.repository.SuborderRepository;

@RunWith(SpringRunner.class)
public class AdminServiceImplTest {
	@TestConfiguration
	static class AdminServiceImplTestConfiguration {
		@Bean
		public AdminService adminService() {
			return new AdminServiceImpl();
		}
	}

	@Autowired
	AdminService adminService;
	@MockBean
	UserRepository userRepo;
	@MockBean
	RestaurantRepository restuarantRepo;
	@MockBean
	SuborderRepository suborderRepo;
	@MockBean
	FoodRepository foodRepository;
	StatisticsDto statisticsDto = new StatisticsDto();
	List<Object> MaximumFood = new ArrayList<Object>();
	FoodsInCartDto foodCartDto = null;
	RestaurantDto restaurantDto = null;
	List<Object> Resturantlist = new ArrayList<Object>();
	int totalUser = 0;

	Restaurant restaurant1 = new Restaurant();
	Restaurant restaurantEntity = new Restaurant();

	ModelMapper ModelMapper = new ModelMapper();

	@Before
	public void setup() {
		statisticsDto.setTotalCustomers(23);
		statisticsDto.setTotalRestaurant(20);
		statisticsDto.setApprovedRestaurant(12);
		statisticsDto.setRejectedRestaurant(3);
		statisticsDto.setPendingRestaurant(7);
		statisticsDto.setOrders(5);
		Set<Food> foods = new HashSet<>();
		Set<Image> images = new HashSet<>();
		Address address = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		Address restaurantAddress = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		restaurantEntity = new Restaurant(1, "Shah Ghouse", "axLbfYkxE6DkV8E07FsM5g==", address, "shahghouse@mail.com",
				"8877996655", 3.8F, 1, foods, images, LocalDateTime.now());
		restaurant1 = new Restaurant(1, "Shah Ghouse", "Admin123", restaurantAddress, "shahghouse@mail.com", "230276",
				3.8F, 0, foods, images, LocalDateTime.now());
	}

	@Before
	public void setupMaximumFood() {
		foodCartDto = new FoodsInCartDto(1, "Biriyani", "Angan");
		MaximumFood.add(foodCartDto);
		foodCartDto = new FoodsInCartDto(2, "Naan", "Curry Leaves");
		MaximumFood.add(foodCartDto);
	}

	@Before
	public void setupTopRestaurant() {
		restaurantDto = new RestaurantDto();
		restaurantDto.setRestaurantId(5);
		restaurantDto.setRestaurantName("Angan");
		Resturantlist.add(restaurantDto);
	}

	@Test
	public void getTopTenMaximumOrderedFood() {
		Mockito.when(suborderRepo.getMaximumTenOrderFood()).thenReturn(MaximumFood);
		assertEquals(MaximumFood.get(0), adminService.getTopTenMaximumOrderedFood().get(0));

	}

	@Test
	public void getTopFiverestaurant() {
		Mockito.when(suborderRepo.getTopFiveRestaurant()).thenReturn(Resturantlist);
		assertEquals(Resturantlist.get(0), adminService.getTopFiverestaurant().get(0));
	}

	@Test
	public void getLeastFiverestaurant() {
		Mockito.when(suborderRepo.getLeastFiveRestaurant()).thenReturn(Resturantlist);
		assertEquals(Resturantlist.get(0), adminService.getLeastFiverestaurant().get(0));
	}

	@Test
	public void getStatisticsCount() {
		Mockito.when(userRepo.getTotalUser()).thenReturn(statisticsDto.getTotalCustomers());
		Mockito.when(restuarantRepo.getAllRestaurant()).thenReturn(statisticsDto.getTotalRestaurant());
		Mockito.when(restuarantRepo.getApprovedRestaurant()).thenReturn(statisticsDto.getApprovedRestaurant());
		Mockito.when(restuarantRepo.getRejectedRestaurant()).thenReturn(statisticsDto.getRejectedRestaurant());
		Mockito.when(restuarantRepo.getPendingApprovalRestaurant()).thenReturn(statisticsDto.getPendingRestaurant());
		Mockito.when(restuarantRepo.getFoodOrders()).thenReturn(statisticsDto.getOrders());
		assertEquals(statisticsDto.getTotalCustomers(), adminService.getStatisticsCount().getTotalCustomers());
	}

	@Test
	public void allRestaurantStatus() {
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(restaurantEntity);
		restaurantList.add(restaurant1);
		Mockito.when(restuarantRepo.findAll()).thenReturn(restaurantList);
		List<RestaurantInfoDto> restaurantFromService = adminService.allRestaurantStatus(1);
		List<RestaurantInfoDto> restaurantFromTest = restaurantList.stream().map(rst->ModelMapper.map(rst, RestaurantInfoDto.class)).collect(Collectors.toList());;
		assertEquals(restaurantFromService.get(0).getRestaurantId(), restaurantFromTest.get(0).getRestaurantId());
		assertNotNull(restaurantFromTest);
	}

	@Test
	public void changeRestaurantStatus() throws  AdminException {
		String response1 = "Restaurant Approved";
		String response2 = "Restaurant Rejected";
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(restaurantEntity);
		restaurantList.add(restaurant1);
		List<Food> foods=new ArrayList<>();
		Mockito.when(foodRepository.saveAll(Mockito.any(Set.class))).thenReturn(foods);
		Mockito.when(restuarantRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(restaurant1))
				.thenReturn(Optional.of(restaurantEntity));
		assertEquals(adminService.changeRestaurantStatus(1, 1), response1);
		assertEquals(adminService.changeRestaurantStatus(1, 0), response2);
	}

	@Test(expected = AdminException.class)
	public void changeRestaurantStatus_Exception() throws  AdminException {
		String response1 = "Restaurant Approved";
		String response2 = "Restaurant Rejected";
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(restaurantEntity);
		restaurantList.add(restaurant1);
		Mockito.when(restuarantRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		adminService.changeRestaurantStatus(1, 1);
	}

}