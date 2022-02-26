package com.mindtree.bookyourmeal.modules.core.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.mindtree.bookyourmeal.modules.core.dto.FoodRestaurantSearchDto;
import com.mindtree.bookyourmeal.modules.core.dto.RestaurantWithoutFoodsDto;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.FoodRestaurantSearch;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.exception.CoreException;
import com.mindtree.bookyourmeal.modules.core.repository.CoreRepository;
import com.mindtree.bookyourmeal.modules.core.repository.SearchRepository;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Category;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Ingredient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Nutrient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.RestaurantRepository;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;
import com.mindtree.bookyourmeal.modules.user.repository.SuborderRepository;

@RunWith(MockitoJUnitRunner.class)
public class CoreServiceImplTest {

	@Mock
	SearchRepository searchrepository;
	@Mock
	FoodRepository foodrepository;
	@Mock
	SuborderRepository subOrderRepos;
	@Mock
	RestaurantRepository restaurantrepository;
	@Mock
	CoreRepository coreRepository;
	@InjectMocks
	CoreServiceImpl coreserviceimpl;
	
	String searchedfood=new String();
	FoodRestaurantSearch result;
	List<Restaurant> restaurants = new ArrayList<Restaurant>();
	List<Food> foodlist = new ArrayList<Food>();
	FoodRestaurantSearchDto frsdto=new FoodRestaurantSearchDto();
	Food food;
	int foodId;
	@Before
	public void setup() {
		foodId=1;
		MockitoAnnotations.initMocks(this);
		Set<Food> foods=new HashSet<Food>();
		Set<Image> images = new HashSet<>();
		Image foodImage = new Image("1", "DoPyaza");
		images.add(new Image("1", "Shah Ghouse"));
		Restaurant restaurant= new Restaurant();
		Set<Ingredient> ingredients = new HashSet<>();
		Nutrient nutrient = new Nutrient(1, 600, 7F, 2, 1.7F, 3.7F, LocalDateTime.now());
		ingredients.add(new Ingredient(1, "Mutton", 150, nutrient, LocalDateTime.now()));
		Category category = new Category(1, "Indian", true, LocalDateTime.now());
		Address restaurantAddress=new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		images.add(new Image("1", "Shah Ghouse"));
		food = new Food(1, "Mutton Do Pyaza", "Slow braised lamb stew", 440, 50, 5.0F, nutrient, 4, category,
				restaurant, foodImage, true, ingredients, LocalDateTime.now());
		foods.add(food);
		restaurant = new Restaurant(1, "Shah Ghouse", "Admin123", restaurantAddress, "shahghouse@mail.com", "230276",
				3.8F, 1,foods, images, LocalDateTime.now());
		restaurants.add(restaurant);
		foodlist.add(food);
		searchedfood = "hot";
		List<FoodDto> foodlistdto=foodlist.stream().map(e->convertFoodEntityToDto(e)).collect(Collectors.toList());
		List<RestaurantWithoutFoodsDto> rlist=restaurants.stream().map(e->convertRestaurantEntityToResWithoutFoodsDto(e)).collect(Collectors.toList());
		frsdto.setFoods(foodlistdto);
		frsdto.setRestaurants(rlist);
	}
	List<Object> restaurantList = new ArrayList<Object>();
	List<Object> foodList = new ArrayList<Object>();
	FoodsInCartDto foodsInCartDto = null;
	@org.junit.Before
	public void setRestaurantLocation(){
		restaurantList.add("Chennai");
		restaurantList.add("Bhubaneswar");
		restaurantList.add("Bangalore");
	    restaurantList.add("Hyderabad");	
	}
	
	@Before
	public void setRecommendedFood() {
		foodsInCartDto = new FoodsInCartDto(1,"Biriyani",100,"Image Id","Angan");
		foodList.add(foodsInCartDto);
	}	

	private ModelMapper modelMapper = new ModelMapper();
	
	private Food convertFoodDtoToEntity(FoodDto foodDto) {
		return modelMapper.map(foodDto, Food.class);
	}

	private FoodDto convertFoodEntityToDto(Food food) {
		return modelMapper.map(food, FoodDto.class);
	}

	private RestaurantWithoutFoodsDto convertRestaurantEntityToResWithoutFoodsDto(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantWithoutFoodsDto.class);
	}

	@Test
	public void searchbykeyword() throws CoreException 
	{	
		System.out.println(searchedfood);
		System.out.println(result);
		result =searchbykeywordresult(searchedfood);
		Mockito.when(searchrepository.searchbykeyword("hot")).thenReturn(result);
		assertEquals(result,searchrepository.searchbykeyword(searchedfood));
		assertEquals(frsdto.getFoods().get(0).getFoodName(),coreserviceimpl.searchbykeyword(searchedfood).getFoods().get(0).getFoodName());
	}

	public FoodRestaurantSearch searchbykeywordresult(String searchfood) 
	{
		FoodRestaurantSearch foodrestaurantsearch = new FoodRestaurantSearch();

		foodrestaurantsearch.setFoods(foodlist);
		foodrestaurantsearch.setRestaurants(restaurants);
		return foodrestaurantsearch;
	}
	@Test
	public void getfoodbyfoodid()
	{
		Mockito.when(foodrepository.getOne(foodId)).thenReturn(food);
		FoodDto fooddto=convertFoodEntityToDto(food);
		assertEquals(fooddto.getFoodName(),coreserviceimpl.getfoodbyfoodid(foodId).getFoodName());
	}
	
	
	
	@Test
	public void fetchRestaurantLocation()
	{
		Mockito.when(restaurantrepository.getAllLocationOfRestaurant()).thenReturn(restaurantList);
		assertEquals(restaurantList,coreserviceimpl.fetchRestaurantLocation());
	}
	
	@Test
	public void fetchRecommendedFood() {
		Mockito.when(subOrderRepos.getMaximumOrderFood()).thenReturn(foodList);
		assertEquals(foodList.get(0),coreserviceimpl.fetchTheRecommendedFood().get(0));
	}
	
	}

