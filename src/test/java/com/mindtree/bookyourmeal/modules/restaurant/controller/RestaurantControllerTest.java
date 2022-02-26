package com.mindtree.bookyourmeal.modules.restaurant.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.restaurant.dto.EditFoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.service.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
	@TestConfiguration
	static class RestaurantServiceImplTestConfiguration {
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
	}

	@Autowired
	MockMvc mockMvc;
	@MockBean
	RestaurantService restaurantService;
	@Autowired
	ModelMapper modelMapper;

	@Test
	public void getFood() throws Exception {
		EditFoodDto editFoodDto = new EditFoodDto();
		editFoodDto.setFoodId(1);
		editFoodDto.setFoodAvailabilityStatus(true);
		List<EditFoodDto> foodList = new ArrayList<EditFoodDto>();
		foodList.add(editFoodDto);
		Mockito.when(restaurantService.getFood(1)).thenReturn(foodList);
		mockMvc.perform(get("/fooditems").param("restaurantId", "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateFood() throws Exception {
		FoodDto foodDto = new FoodDto();
		foodDto.setFoodId(2);
		foodDto.setFoodName("paneer tikka");
		foodDto.setFoodAvailabilityStatus(true);
		Mockito.when(restaurantService.updateFood(1, 1)).thenReturn(foodDto);
		mockMvc.perform(get("/updatefooditem").param("foodid", "2").param("availibilityStatus", "1")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void loginRestaurant() throws Exception {
		Set<Food> foods = new HashSet<>();
		Set<Image> images = new HashSet<>();
		images.add(new Image("1", "Shah Ghouse"));
		Address restaurantAddress = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		RestaurantDto restaurantDto = new RestaurantDto(1, "Shah Ghouse", "Admin123", restaurantAddress,
				"shahghouse@mail.com", "9121230276", 3.8F, 1, foods, images, LocalDateTime.now());
		RestaurantInfoDto retaurantInfoDto = modelMapper.map(restaurantDto, RestaurantInfoDto.class);
		System.err.println(retaurantInfoDto.toString());
		Mockito.when(restaurantService.loginRestaurant(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(retaurantInfoDto);
		mockMvc.perform(get("/login-restaurant").param("mailId", "shahghouse@mail.com").param("password", "Admin123")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$[0].restaurantId").exists());
	}

	@Test
	public void registerRestaurant() throws Exception {
//		Set<Food> foods = new HashSet<>();
//		Set<Image> images = new HashSet<>();
//		images.add(new Image("1", "Shah Ghouse"));
		Address restaurantAddress = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				null);
		RestaurantDto restaurantDto = new RestaurantDto(1, "Shah Ghouse", "Admin123", restaurantAddress,
				"shahghouse@mail.com", "2121230276", 3.8F, 1, null, null, null);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(restaurantDto);
		RestaurantInfoDto retaurantInfoDto = modelMapper.map(restaurantDto, RestaurantInfoDto.class);
		System.out.println(restaurantDto);
		System.out.println(json);
		String body = "{\"restaurantId\":1," + "\"restaurantName\":\"restaurant\","
				+ "	\"restaurantContactNumber\":9999999999," + "\"mailId\":\"admin@gmail.com\","
				+ "	\"restaurantPassword\":\"Admin@123\"," + "\"restaurantRating\":0," + "	\"restaurantStatus\":0,"
				+ "	\"restaurantAddress\":{" + "\"addressId\":1," + "		\"doorNumber\":\"0\","
				+ "		\"streetName\":\"default\"," + "		\"locality\":\"default\","
				+ "		\"city\":\"default\"," + "		\"zipcode\":\"111111\"," + "		\"state\":\"default\""
				+ "	}," + "	\"foods\":null," + "	\"restaurantImages\":null" + "}";
		Mockito.when(restaurantService.registerRestaurant(Mockito.any(RestaurantDto.class)))
				.thenReturn(retaurantInfoDto);
//		Mockito.verify(restaurantService).registerRestaurant(restaurantDto);
//		mockMvc.perform(post("/register-restaurant").content(json).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register-restaurant")
				.accept(MediaType.APPLICATION_JSON).content(body).characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
