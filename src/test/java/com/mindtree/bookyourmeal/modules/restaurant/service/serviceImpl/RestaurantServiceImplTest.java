
package com.mindtree.bookyourmeal.modules.restaurant.service.serviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.repository.AddressRepository;
import com.mindtree.bookyourmeal.modules.core.repository.ImageRepository;
import com.mindtree.bookyourmeal.modules.restaurant.dto.EditFoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Category;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Ingredient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Nutrient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantException;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.IngredientRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.NutrientRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.RestaurantRepository;
import com.mindtree.bookyourmeal.modules.restaurant.service.RestaurantService;
import com.mindtree.bookyourmeal.modules.restaurant.service.serviceimplementation.RestaurantServiceImpl;

@RunWith(SpringRunner.class)
public class RestaurantServiceImplTest {

	@TestConfiguration
	static class RestaurantServiceImplTestConfiguration {
		@Bean
		public RestaurantService restaurantService() {
			return new RestaurantServiceImpl();
		}
	}

	@Autowired
	RestaurantService restaurantService;
	@MockBean
	RestaurantRepository restaurantRepo;
	@MockBean
	FoodRepository foodRepo;
	@MockBean
	AddressRepository addressRepo;
	@MockBean
	ImageRepository imageRepo;
	@MockBean
	NutrientRepository nutrientRepo;
	@MockBean
	IngredientRepository ingredientRepo;
	private Restaurant restaurantEntity;

	ModelMapper modelMapper = new ModelMapper();
	Ingredient ingredient1 = new Ingredient();
	Ingredient ingredient2 = new Ingredient();
	Restaurant restaurant1 = new Restaurant();
	RestaurantDto restaurantdto = new RestaurantDto();

	@org.junit.Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(1);
		restaurant.setRestaurantName("aangan");
		Set<Food> foods = new HashSet<>();
		Set<Image> images = new HashSet<>();
		images.add(new Image("1", "Shah Ghouse"));
		Address address = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		restaurantEntity = new Restaurant(1, "Shah Ghouse", "axLbfYkxE6DkV8E07FsM5g==", address, "shahghouse@mail.com",
				"8877996655", 3.8F, 1, foods, images, LocalDateTime.now());
		Food food = new Food();
		Set<Food> foodList = new HashSet<Food>();
		restaurant.setRestaurantId(1);
		restaurant.setRestaurantName("aangan");
		food.setFoodId(2);
		food.setFoodName("paneer tikka");
		food.setFoodAvailabilityStatus(true);
		foodList.add(food);
		restaurant.setFoods(foodList);
		Set<Food> foods1 = new HashSet<Food>();
		Set<Image> images1 = new HashSet<>();
		Image foodImage = new Image("1", "DoPyaza");
		images1.add(new Image("1", "Shah Ghouse"));
		Set<Ingredient> ingredients = new HashSet<>();
		Nutrient nutrient = new Nutrient(1, 600, 7F, 2, 1.7F, 3.7F, LocalDateTime.now());
		ingredients.add(new Ingredient(1, "Mutton", 150, nutrient, LocalDateTime.now()));
		Category category = new Category(1, "Indian", true, LocalDateTime.now());
		Address restaurantAddress = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		images1.add(new Image("1", "Shah Ghouse"));
		food = new Food(1, "Mutton Do Pyaza", "Slow braised lamb stew", 440, 50, 5.0F, nutrient, 4, category,
				restaurant1, foodImage, true, ingredients, LocalDateTime.now());
		foods1.add(food);
		restaurant1 = new Restaurant(1, "Shah Ghouse", "Admin123", restaurantAddress, "shahghouse@mail.com", "230276",
				3.8F, 0, foods1, images1, LocalDateTime.now());
		restaurantdto = convertRestaurantEntityToDto(restaurant1);
	}

	private RestaurantDto convertRestaurantEntityToDto(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDto.class);
	}

	@Test
	public void getFood() throws RestaurantException {

		MockitoAnnotations.initMocks(this);
		Restaurant restaurant = new Restaurant();
		Food food = new Food();
		Set<Food> foodList = new HashSet<Food>();
		restaurant.setRestaurantId(1);
		restaurant.setRestaurantName("aangan");
		food.setFoodId(2);
		food.setFoodName("paneer tikka");
		food.setFoodAvailabilityStatus(true);
		foodList.add(food);
		restaurant.setFoods(foodList);

		Mockito.when(restaurantRepo.getOne(1)).thenReturn(restaurant);

		Mockito.when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));

		List<EditFoodDto> editFoodList = new ArrayList<>();
		for (Food food1 : foodList) {
			EditFoodDto e = new EditFoodDto();
			e.setFoodId(food.getFoodId());
			e.setFoodName(food.getFoodName());
			e.setFoodAvailabilityStatus(food.isFoodAvailabilityStatus());
			editFoodList.add(e);
		}
		assertEquals(editFoodList.get(0).getFoodName(), restaurantService.getFood(1).get(0).getFoodName());
	}

	@Test
	public void loginRestaurantTest() throws RestaurantException {
		String mail = "shahghouse@mail.com";
		String password = "Jeevan@123";
		Mockito.when(restaurantRepo.findByMailId(Mockito.anyString())).thenReturn(Optional.of(restaurantEntity));
		RestaurantInfoDto restaurantFromService = restaurantService.loginRestaurant(mail, password);
		RestaurantInfoDto restaurantFromTest = modelMapper.map(restaurantEntity, RestaurantInfoDto.class);
//		restaurantFromTest.setRestaurantId(restaurantEntity.getRestaurantId());
//		restaurantFromTest.setRestaurantName(restaurantEntity.getRestaurantName());
//		restaurantFromTest.setMailId(restaurantEntity.getMailId());
//		restaurantFromTest.setRestaurantContactNumber(restaurantEntity.getRestaurantContactNumber());
//		restaurantFromTest.setRestaurantAddress(restaurantEntity.getRestaurantAddress());
//		restaurantFromTest.setRestaurantImages(restaurantEntity.getRestaurantImages());
//		restaurantFromTest.setRestaurantRating(restaurantEntity.getRestaurantRating());
//		restaurantFromTest.setRestaurantStatus(restaurantEntity.getRestaurantStatus());
//		restaurantFromTest.setLocalDateTime(restaurantEntity.getLocalDateTime());
		assertEquals(restaurantFromService.getRestaurantId(), restaurantFromTest.getRestaurantId());
		assertEquals(restaurantFromService.getMailId(), restaurantFromTest.getMailId());
		assertEquals(restaurantFromService.getRestaurantContactNumber(),
				restaurantFromTest.getRestaurantContactNumber());
		assertEquals(restaurantFromService.getRestaurantName(), restaurantFromTest.getRestaurantName());
		assertEquals(restaurantFromService.getRestaurantRating(), restaurantFromTest.getRestaurantRating());
		assertEquals(restaurantFromService.getRestaurantImages(), restaurantFromTest.getRestaurantImages());
		assertEquals(restaurantFromService.getLocalDateTime(), restaurantFromTest.getLocalDateTime());
		assertEquals(restaurantFromService.getRestaurantAddress().getAddressId(),
				restaurantFromTest.getRestaurantAddress().getAddressId());
		assertEquals(restaurantFromService.getRestaurantAddress().getCity(),
				restaurantFromTest.getRestaurantAddress().getCity());

		assertEquals(restaurantFromService.getRestaurantAddress().getDoorNumber(),
				restaurantFromTest.getRestaurantAddress().getDoorNumber());

		assertEquals(restaurantFromService.getRestaurantAddress().getLocality(),
				restaurantFromTest.getRestaurantAddress().getLocality());

		assertEquals(restaurantFromService.getRestaurantAddress().getState(),
				restaurantFromTest.getRestaurantAddress().getState());
		assertEquals(restaurantFromService.getRestaurantAddress().getStreetName(),
				restaurantFromTest.getRestaurantAddress().getStreetName());
		assertEquals(restaurantFromService.getRestaurantAddress().getZipcode(),
				restaurantFromTest.getRestaurantAddress().getZipcode());
		assertEquals(restaurantFromService.getRestaurantAddress().getLocalDateTime(),
				restaurantFromTest.getRestaurantAddress().getLocalDateTime());

	}

	@Test
	public void registerRestaurantTest() throws RestaurantException {
		Mockito.when(restaurantRepo.findByMailId(restaurantEntity.getMailId())).thenReturn(Optional.empty());
		Mockito.when(restaurantRepo.findByRestaurantContactNumber(restaurantEntity.getRestaurantContactNumber()))
				.thenReturn(Optional.empty()).thenReturn(Optional.of(restaurantEntity));
		Mockito.when(restaurantRepo.save(Mockito.any(Restaurant.class))).thenReturn(restaurantEntity);
		RestaurantDto restaurantDto = modelMapper.map(restaurantEntity, RestaurantDto.class);
//		restaurantDto.setRestaurantId(restaurantEntity.getRestaurantId());
//		restaurantDto.setRestaurantName(restaurantEntity.getRestaurantName());
//		restaurantDto.setMailId(restaurantEntity.getMailId());
		restaurantDto.setRestaurantPassword("Jeevan@123");
//		restaurantDto.setRestaurantContactNumber(restaurantEntity.getRestaurantContactNumber());
//		restaurantDto.setRestaurantAddress(restaurantEntity.getRestaurantAddress());
//		restaurantDto.setRestaurantImages(restaurantEntity.getRestaurantImages());
//		restaurantDto.setRestaurantRating(restaurantEntity.getRestaurantRating());
//		restaurantDto.setRestaurantStatus(restaurantEntity.getRestaurantStatus());
//		restaurantDto.setFoods(restaurantEntity.getFoods());
		RestaurantInfoDto restaurantFromService = restaurantService.registerRestaurant(restaurantDto);
		RestaurantInfoDto restaurantFromTest = modelMapper.map(restaurantEntity, RestaurantInfoDto.class);
//		restaurantFromTest.setRestaurantId(restaurantEntity.getRestaurantId());
//		restaurantFromTest.setRestaurantName(restaurantEntity.getRestaurantName());
//		restaurantFromTest.setMailId(restaurantEntity.getMailId());
//		restaurantFromTest.setRestaurantContactNumber(restaurantEntity.getRestaurantContactNumber());
//		restaurantFromTest.setRestaurantAddress(restaurantEntity.getRestaurantAddress());
//		restaurantFromTest.setRestaurantImages(restaurantEntity.getRestaurantImages());
//		restaurantFromTest.setRestaurantRating(restaurantEntity.getRestaurantRating());
//		restaurantFromTest.setRestaurantStatus(restaurantEntity.getRestaurantStatus());
//		restaurantFromTest.setLocalDateTime(restaurantEntity.getLocalDateTime());
		assertEquals(restaurantFromService.getRestaurantId(), restaurantFromTest.getRestaurantId());
		assertNotNull(restaurantFromTest);
	}

	@Test
	public void updateFood() throws RestaurantException {
		Food food = new Food();
		food.setFoodId(2);
		food.setFoodName("paneer tikka");
		food.setFoodAvailabilityStatus(true);
		int availibilityStatus = 1;

		Mockito.when(foodRepo.getOne(2)).thenReturn(food);
		Mockito.when(foodRepo.findById(2)).thenReturn(Optional.of(food));

		if (availibilityStatus == 1)
			food.setFoodAvailabilityStatus(true);
		FoodDto foodDto = convertFoodEntityToDto(food);
		foodRepo.save(food);
		assertEquals(foodDto.isFoodAvailabilityStatus(), restaurantService.updateFood(2, 1).isFoodAvailabilityStatus());

		int availibilityStatus1 = 0;

		Mockito.when(foodRepo.getOne(2)).thenReturn(food);
		Mockito.when(foodRepo.findById(2)).thenReturn(Optional.of(food));

		if (availibilityStatus1 == 0)
			food.setFoodAvailabilityStatus(false);

		FoodDto foodDto1 = convertFoodEntityToDto(food);
		foodRepo.save(food);
		assertEquals(foodDto1.isFoodAvailabilityStatus(),
				restaurantService.updateFood(2, 0).isFoodAvailabilityStatus());

	}

	private FoodDto convertFoodEntityToDto(Food food) {
		return modelMapper.map(food, FoodDto.class);
	}

	@Test
	public void calculateNutrientScore() throws RestaurantException {
		Nutrient nutrient = new Nutrient();
		nutrient.setCholestrol(11);
		nutrient.setMufa(22);
		nutrient.setNoOfCalories(23);
		nutrient.setProtein(15);
		nutrient.setPufa(8);
		double nutrientTotal = nutrient.getCholestrol() + nutrient.getMufa() + nutrient.getNoOfCalories()
				+ nutrient.getProtein() + nutrient.getPufa();
		int nutrientScore = (int) ((nutrientTotal) / 100);
		assertEquals(nutrientScore, restaurantService.calculateNutrientScore(nutrient));
	}

	@Test
	public void calculateNutrient() throws RestaurantException {
		ingredient1.setIngredientId(1);
		ingredient1.setIngredientName("chicken");
		Nutrient nutrient = new Nutrient();
		nutrient.setCholestrol(11);
		nutrient.setMufa(22);
		nutrient.setNoOfCalories(23);
		nutrient.setProtein(15);
		nutrient.setPufa(8);
		ingredient1.setNutrient(nutrient);
		Set<Ingredient> ingredientSet = new HashSet<>();
		ingredientSet.add(ingredient1);
		List<Ingredient> ingrediantList = new ArrayList<Ingredient>();
		ingrediantList.add(ingredient1);
		int noOfCalorie = 0;
		float protein = 0;
		int cholestrol = 0;
		float mufa = 0;
		float pufa = 0;
		Mockito.when(ingredientRepo.findAll()).thenReturn(ingrediantList);
		Nutrient nutrient1 = ingredient1.getNutrient();
		noOfCalorie += nutrient1.getNoOfCalories();
		protein += nutrient1.getProtein();
		cholestrol += nutrient1.getCholestrol();
		mufa += nutrient1.getMufa();
		pufa += nutrient1.getPufa();
		Nutrient nutrient2 = new Nutrient();
		nutrient2.setNoOfCalories(noOfCalorie);
		nutrient2.setCholestrol(cholestrol);
		nutrient2.setMufa(mufa);
		nutrient2.setProtein(protein);
		nutrient2.setPufa(pufa);
		Optional<Ingredient> ingOptional = Optional.of(ingredient1);
		Mockito.when(ingredientRepo.findById(1)).thenReturn(ingOptional);
		Mockito.when(nutrientRepo.save(nutrient2)).thenReturn(nutrient2);
		assertEquals(nutrient2.getCholestrol(), restaurantService.calculateNutrient(ingredientSet).getCholestrol());
	}

	@Test
	public void getrestaurantbyrestaurantid() throws RestaurantException {

		Mockito.when(restaurantRepo.getOne(1)).thenReturn(restaurant1);
		Mockito.when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant1));
		assertEquals(restaurantdto.getRestaurantName(),
				restaurantService.getrestaurantbyrestaurantid(1).getRestaurantName());
	}
}
