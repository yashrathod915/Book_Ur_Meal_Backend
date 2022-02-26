package com.mindtree.bookyourmeal.modules.core.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.controller.CoreController;
import com.mindtree.bookyourmeal.modules.core.dto.FoodRestaurantSearchDto;
import com.mindtree.bookyourmeal.modules.core.dto.RestaurantWithoutFoodsDto;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.entity.Role;
import com.mindtree.bookyourmeal.modules.core.exception.CoreException;
import com.mindtree.bookyourmeal.modules.core.service.impl.CoreServiceImpl;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Category;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Ingredient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Nutrient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;

@RunWith(SpringRunner.class)
public class CoreControllerTest {
	
	private MockMvc mockMvc;
	@InjectMocks
	CoreController coreController;
	@Mock
	CoreServiceImpl coreService;
    List<Object> restaurantLocation = new ArrayList<>();
    List<Object> foodList = new ArrayList<Object>();
	FoodsInCartDto foodsInCartDto = null;
	FoodRestaurantSearchDto foodrestaurantsearchdto=new FoodRestaurantSearchDto();
	FoodDto fooddto=new FoodDto();
	private ModelMapper modelMapper = new ModelMapper();
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		List<FoodDto> foodlist=new ArrayList<FoodDto>();
		List<RestaurantWithoutFoodsDto> restaurants=new ArrayList<RestaurantWithoutFoodsDto>();
		mockMvc = MockMvcBuilders.standaloneSetup(coreController).build();
		restaurantLocation.add("Chennai");
		restaurantLocation.add("Bangalore");
		foodsInCartDto = new FoodsInCartDto(1,"Biriyani",100,"Image Id","Angan");
		foodList.add(foodsInCartDto);
		Set<Food> foods = new HashSet<>();
		Address address = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		Nutrient nutrient = new Nutrient(1, 600, 7F, 2, 1.7F, 3.7F, LocalDateTime.now());
		Set<Image> images = new HashSet<>();
		images.add(new Image("1", "Shah Ghouse"));
		Image foodImage = new Image("1", "DoPyaza");
		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(new Ingredient(1, "Mutton", 150, nutrient, LocalDateTime.now()));
		Restaurant restaurant = new Restaurant(1, "Shah Ghouse", "Admin123", address, "shahghouse@mail.com", "230276",
				3.8F, 1, foods, images, LocalDateTime.now());
		Category category = new Category(1, "Indian", true, LocalDateTime.now());
		Food food = new Food(1, "Mutton Do Pyaza", "Slow braised lamb stew", 440, 50, 5.0F, nutrient, 4, category,
				restaurant, foodImage, true, ingredients, LocalDateTime.now());	
		fooddto=modelMapper.map(food, FoodDto.class);
		RestaurantWithoutFoodsDto restaurantdto=modelMapper.map(restaurant, RestaurantWithoutFoodsDto.class);
		foodlist.add(fooddto);
		restaurants.add(restaurantdto);
		foodrestaurantsearchdto.setFoods(foodlist);
		foodrestaurantsearchdto.setRestaurants(restaurants);
		
	}
	
	@Test
	public void  getfoodbyfoodid() throws Exception {
		Mockito.when(coreService.getfoodbyfoodid(1)).thenReturn(fooddto);
		mockMvc.perform(get("/getfoodbyfoodid").param("foodId","1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void getTheRestaurantLocations() throws Exception {
		coreService.fetchRestaurantLocation();
		Mockito.when(coreService.fetchRestaurantLocation()).thenReturn(restaurantLocation);
		mockMvc.perform(MockMvcRequestBuilders.get("/fetchTheResaturantLocation").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

}
	@Test
	public void getRecommendedFood() throws Exception {
		Mockito.when(coreService.fetchTheRecommendedFood()).thenReturn(foodList);
		mockMvc.perform(MockMvcRequestBuilders.get("/fetchTheCommendedFood").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void searchbykeyword() throws Exception {
		Mockito.when(coreService.searchbykeyword("food")).thenReturn(foodrestaurantsearchdto);
		mockMvc.perform(get("/searchbykeyword").param("searchedfood","food").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());		
	}
	
}
