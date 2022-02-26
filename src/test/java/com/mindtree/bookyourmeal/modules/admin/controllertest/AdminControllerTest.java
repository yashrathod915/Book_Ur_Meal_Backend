package com.mindtree.bookyourmeal.modules.admin.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.bookyourmeal.modules.admin.controller.AdminController;
import com.mindtree.bookyourmeal.modules.admin.dto.StatisticsDto;
import com.mindtree.bookyourmeal.modules.admin.service.serviceImpl.AdminServiceImpl;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;

@RunWith(SpringRunner.class)
public class AdminControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	AdminController adminController;
	@Mock
	AdminServiceImpl adminServiceImpl;
	ModelMapper modelMapper = new ModelMapper();
	StatisticsDto statisticsDto = new StatisticsDto();
	List<Object> MaximumFood = new ArrayList<Object>();
	FoodsInCartDto foodCartDto = null;
	RestaurantDto restaurantDto = null;
	List<Object> Resturantlist = new ArrayList<Object>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
		statisticsDto.setTotalCustomers(23);
		statisticsDto.setTotalRestaurant(20);
		statisticsDto.setApprovedRestaurant(12);
		statisticsDto.setRejectedRestaurant(3);
		statisticsDto.setPendingRestaurant(7);
		statisticsDto.setOrders(5);
		foodCartDto = new FoodsInCartDto(1, "Biriyani", "Angan");
		MaximumFood.add(foodCartDto);
		foodCartDto = new FoodsInCartDto(2, "Naan", "Curry Leaves");
		MaximumFood.add(foodCartDto);
		restaurantDto = new RestaurantDto();
		restaurantDto.setRestaurantId(5);
		restaurantDto.setRestaurantName("Angan");
		Resturantlist.add(restaurantDto);
	}

	@Test
	public void getTheStatisticsRepost() throws Exception {
		Mockito.when(adminServiceImpl.getStatisticsCount()).thenReturn(statisticsDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/getStatisticsCount").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getTopTenFood() throws Exception {
		Mockito.when(adminServiceImpl.getTopTenMaximumOrderedFood()).thenReturn(MaximumFood);
		mockMvc.perform(MockMvcRequestBuilders.get("/getTopTenFoods").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getTopFiveRestaurant() throws Exception {
		Mockito.when(adminServiceImpl.getTopFiverestaurant()).thenReturn(Resturantlist);
		mockMvc.perform(MockMvcRequestBuilders.get("/getTopFiveRestaurant").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getTopLeastRestaurant() throws Exception {
		Mockito.when(adminServiceImpl.getTopFiverestaurant()).thenReturn(Resturantlist);
		mockMvc.perform(MockMvcRequestBuilders.get("/getLeastFiveRestaurant").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getStatus() throws Exception {
		Set<Food> foods = new HashSet<>();
		Set<Image> images = new HashSet<>();
		Set<RestaurantDto> restaurantDtoList = new HashSet<RestaurantDto>();
		images.add(new Image("1", "Shah Ghouse"));
		Address restaurantAddress = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		restaurantDtoList.add(new RestaurantDto(1, "Shah Ghouse", "Admin123", restaurantAddress, "shahghouse@mail.com",
				"2121230276", 3.8F, 1, foods, images, LocalDateTime.now()));
		restaurantDtoList.add(new RestaurantDto(2, "Shah Ghouse", "Admin123", restaurantAddress, "shahghouse@mail.com",
				"2121230276", 3.8F, 1, foods, images, LocalDateTime.now()));
		List<RestaurantInfoDto> retaurantInfoDtoList = restaurantDtoList.stream()
				.map(x -> modelMapper.map(x, RestaurantInfoDto.class)).collect(Collectors.toList());
		Mockito.when(adminServiceImpl.allRestaurantStatus(Mockito.anyInt())).thenReturn(retaurantInfoDtoList);
		mockMvc.perform(get("/getRestaurantStatus").param("status", "0").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void changeStatus() throws Exception {
		Mockito.when(adminServiceImpl.changeRestaurantStatus(1, 1)).thenReturn("Restaurant Approved");
		mockMvc.perform(post("/changeRestaurantStatus").param("restaurantId", "1").param("request", "0")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

}
