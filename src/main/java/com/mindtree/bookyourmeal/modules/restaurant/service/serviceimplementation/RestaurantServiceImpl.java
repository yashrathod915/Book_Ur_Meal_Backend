
package com.mindtree.bookyourmeal.modules.restaurant.service.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.repository.AddressRepository;
import com.mindtree.bookyourmeal.modules.core.repository.ImageRepository;
import com.mindtree.bookyourmeal.modules.core.util.AES;
import com.mindtree.bookyourmeal.modules.restaurant.dto.EditFoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Ingredient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Nutrient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.restaurant.exception.EmailAlreadyExistsException;
import com.mindtree.bookyourmeal.modules.restaurant.exception.ErrorConstants;
import com.mindtree.bookyourmeal.modules.restaurant.exception.IncorrectPasswordException;
import com.mindtree.bookyourmeal.modules.restaurant.exception.InvalidEmailException;
import com.mindtree.bookyourmeal.modules.restaurant.exception.InvalidNumberException;
import com.mindtree.bookyourmeal.modules.restaurant.exception.InvalidZipcodeException;
import com.mindtree.bookyourmeal.modules.restaurant.exception.PhoneNumberAlreadyExists;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantException;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantNotFoundException;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.IngredientRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.NutrientRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.RestaurantRepository;
import com.mindtree.bookyourmeal.modules.restaurant.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	FoodRepository foodRepo;
	@Autowired
	RestaurantRepository restaurantRepo;
	@Autowired
	AddressRepository addressRepo;
	@Autowired
	ImageRepository imageRepo;
	@Autowired
	NutrientRepository nutrientRepo;
	@Autowired
	IngredientRepository ingredientRepo;

	AES encryption = new AES();

	private ModelMapper modelMapper = new ModelMapper();

	private Food convertFoodDtoToEntity(FoodDto foodDto) {
		return modelMapper.map(foodDto, Food.class);
	}

	private FoodDto convertFoodEntityToDto(Food food) {
		return modelMapper.map(food, FoodDto.class);
	}

	private Restaurant convertRestaurantDtoToEntity(RestaurantDto restaurantDto) {
		return modelMapper.map(restaurantDto, Restaurant.class);
	}

	private RestaurantDto convertRestaurantEntityToDto(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDto.class);
	}
	
	private RestaurantInfoDto convertRestaurantInfoEntityToDto(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantInfoDto.class);
	}
	
	public RestaurantInfoDto registerRestaurant(RestaurantDto restaurantDto) throws RestaurantException {
		Restaurant restaurant = convertRestaurantDtoToEntity(restaurantDto);
		System.err.println(restaurant);
		if (validateEmail(restaurant.getMailId()) && validatePassword(restaurant.getRestaurantPassword())
				&& validateNumber(restaurant.getRestaurantContactNumber())
				&& validateInput(restaurant.getRestaurantName())
				&& validateInput(restaurant.getRestaurantAddress().getLocality())
				&& validateInput(restaurant.getRestaurantAddress().getCity())
				&& validateInput(restaurant.getRestaurantAddress().getState())
				&& validateZipcode(restaurant.getRestaurantAddress().getZipcode())) {
			Optional<Restaurant> opRestaurant = restaurantRepo.findByMailId(restaurant.getMailId());
			if (opRestaurant.isPresent())
				throw new EmailAlreadyExistsException("Email  already Exists");
			opRestaurant = restaurantRepo.findByRestaurantContactNumber(restaurant.getRestaurantContactNumber());
			if (opRestaurant.isPresent())
				throw new PhoneNumberAlreadyExists("Number already Exists");
			String str = AES.encrypt(restaurant.getRestaurantPassword());
			restaurant.setRestaurantPassword(str);
			return convertRestaurantInfoEntityToDto(restaurantRepo.save(restaurant));
		}
		throw new RestaurantException("Not valid Credentials");
	}

	public RestaurantInfoDto loginRestaurant(String mailId, String password) throws RestaurantException {
		System.err.println();
		if (validateEmail(mailId) && validatePassword(password)) {
			Optional<Restaurant> opRestaurant = restaurantRepo.findByMailId(mailId);
			opRestaurant.orElseThrow(() -> new RestaurantNotFoundException("Not Valid Credentials"));
			Restaurant restaurant = opRestaurant.get();
			String str = AES.decrypt(restaurant.getRestaurantPassword());
			if (password.equals(str)) {
				if (restaurant.getRestaurantStatus() == 1) {
					return convertRestaurantInfoEntityToDto(restaurant);
				}
				throw new RestaurantNotFoundException("Please Wait for Your Approval");
			}
			throw new IncorrectPasswordException("Enter Correct Password");
		} else
			throw new RestaurantException("Incorrect mail id or password");
	}

	public boolean validateEmail(String email) throws RestaurantException {
		System.err.println("email");
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		if (pattern.matcher(email).matches())
			return true;
		throw new InvalidEmailException("Enter valid Email");

	}

	public boolean validateNumber(String number) throws RestaurantException {
		System.err.println("number" + number);
		String numberRegex = "[7-9][0-9]*{9}";
		Pattern pattern = Pattern.compile(numberRegex);
		if (number != null && pattern.matcher(number).matches())
			return true;
		throw new InvalidNumberException("Enter Valid Number");

	}

	public boolean validateZipcode(String zipcode) throws RestaurantException {
		System.err.println("zipcode");
		String zipcodeRegex = "[0-9]{6}";
		Pattern pattern = Pattern.compile(zipcodeRegex);
		if (zipcode != null && pattern.matcher(zipcode).matches())
			return true;
		throw new InvalidZipcodeException("Enter Valid Zipcode");

	}

	public boolean validateInput(String name) throws RestaurantException {
		System.err.println("name");
		String nameRegex = "[a-zA-Z0-9][a-zA-Z0-9',-/ ]*";
		Pattern pattern = Pattern.compile(nameRegex);
		if (name != null && pattern.matcher(name).matches())
			return true;
		throw new InvalidNumberException("Enter Valid Input " + name);

	}

	public boolean validatePassword(String password) throws RestaurantException {
		System.err.println("password");
		String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{8,}";
		Pattern pattern = Pattern.compile(passwordRegex);
		if (password != null && pattern.matcher(password).matches())
			return true;
		throw new IncorrectPasswordException("Enter Valid Password");
	}

	public RestaurantDto getrestaurantbyrestaurantid(int restaurantId) throws RestaurantException {
		Restaurant restaurant = restaurantRepo.getOne(restaurantId);
		Optional<Restaurant> restaurantOptional = restaurantRepo.findById(restaurantId);
		restaurantOptional.orElseThrow(() -> new RestaurantException(ErrorConstants.NOSUCHRESTAURANT));
		return convertRestaurantEntityToDto(restaurant);
	}

	@Override
	public List<Object> fetchRestaurantByCity(String city) {
		return restaurantRepo.getRestaurantByCity(city);
	}


	@Override
	public FoodDto addFood(FoodDto fooddto) throws RestaurantException {
		System.err.println(fooddto);
		Food food = convertFoodDtoToEntity(fooddto);
		Image image = food.getFoodImage();
		Image image1 = imageRepo.getOne(image.getPictureId());
		food.setFoodImage(image1);

		List<String> foodNameList = foodRepo.getFoodNameList(fooddto.getRestaurant().getRestaurantId());
		System.out.println(foodNameList);
		for (String food2 : foodNameList) {
			if (fooddto.getFoodName().equalsIgnoreCase(food2)) {
				throw new RestaurantException(ErrorConstants.FOODNAMEALREADYAVAILABLE);
			}
		}
		Nutrient nutrient = calculateNutrient(food.getIngredients());
		if (nutrient == null) {
			throw new RestaurantException(ErrorConstants.NOSUCHINGREDIENT);
		}
		food.setNutrient(nutrient);
		int nutrientScore = calculateNutrientScore(nutrient);
		food.setNutrientScore(nutrientScore);
		int foodQuantity = food.getFoodQuantity();
		if (foodQuantity == 0)
			throw new RestaurantException(ErrorConstants.FOODQUANTITYNOTAVAILABLE);

		int foodPrice = food.getFoodPrice();
		if (foodPrice == 0)
			throw new RestaurantException(ErrorConstants.FOODPRICENOTAVAILABLE);

		foodRepo.save(food);
		return fooddto;
	}

	public int calculateNutrientScore(Nutrient nutrient) throws RestaurantException {
		double nutrientTotal = nutrient.getCholestrol() + nutrient.getMufa() + nutrient.getNoOfCalories()
				+ nutrient.getProtein() + nutrient.getPufa();
		int nutrientScore = (int) ((nutrientTotal) / 100);
		return nutrientScore;
	}

	public Nutrient calculateNutrient(Set<Ingredient> set) throws RestaurantException {
		int noOfCalorie = 0;
		float protein = 0;
		int cholestrol = 0;
		float mufa = 0;
		float pufa = 0;
		List<Ingredient> ingrediantList = ingredientRepo.findAll();
		for (Ingredient ingredient : set) {

			String ingredientName = ingredient.getIngredientName();
			int ingrediantId = 0;
			Optional<Ingredient> ingredient2 = null;
			for (Ingredient i : ingrediantList) {
				if (i.getIngredientName().equalsIgnoreCase(ingredientName)) {
					ingrediantId = i.getIngredientId();
					ingredient2 = ingredientRepo.findById(ingrediantId);
					if (ingredient2.isPresent()) {
						ingredient2.orElseThrow(() -> new RestaurantException(ErrorConstants.FOODNAMEALREADYAVAILABLE));
						Nutrient nutrient = ingredient2.get().getNutrient();
						if (nutrient != null)
							break;
						System.out.println(ingrediantId);
					} else
						throw new RestaurantException("ingredient not found");
				}
			}
			if (ingredient2 != null) {
				Nutrient nutrient = ingredient2.get().getNutrient();
				noOfCalorie += nutrient.getNoOfCalories();
				protein += nutrient.getProtein();
				cholestrol += nutrient.getCholestrol();
				mufa += nutrient.getMufa();
				pufa += nutrient.getPufa();
			} else
				throw new RestaurantException("No such ingredient found");

		}
		Nutrient nutrient = new Nutrient();
		nutrient.setNoOfCalories(noOfCalorie);
		nutrient.setCholestrol(cholestrol);
		nutrient.setMufa(mufa);
		nutrient.setProtein(protein);
		nutrient.setPufa(pufa);
		nutrientRepo.save(nutrient);
		return nutrient;
	}

	@Override
	public List<EditFoodDto> getFood(int restaurantId) throws RestaurantException {
		Restaurant restaurant = restaurantRepo.getOne(restaurantId);
		Set<Food> foodList = restaurant.getFoods();

		Optional<Restaurant> restaurantOptional = restaurantRepo.findById(restaurantId);
		restaurantOptional.orElseThrow(() -> new RestaurantException(ErrorConstants.NOSUCHRESTAURANT));

		List<EditFoodDto> editFoodList = new ArrayList<>();
		for (Food food : foodList) {
			EditFoodDto e = new EditFoodDto();
			e.setFoodId(food.getFoodId());
			e.setFoodName(food.getFoodName());
			e.setFoodAvailabilityStatus(food.isFoodAvailabilityStatus());
			editFoodList.add(e);
		}

		return editFoodList;
	}

	@Override
	public FoodDto updateFood(int foodId, int availibilityStatus) throws RestaurantException {
		Food food = foodRepo.getOne(foodId);
		Optional<Food> food1 = foodRepo.findById(foodId);
		food1.orElseThrow(() -> new RestaurantException(ErrorConstants.FOODITEMNOTFOUND));
		if (availibilityStatus == 0)
			food.setFoodAvailabilityStatus(false);
		else if (availibilityStatus == 1)
			food.setFoodAvailabilityStatus(true);
		foodRepo.save(food);
		FoodDto foodDto = convertFoodEntityToDto(food);
		return foodDto;
	}

}
