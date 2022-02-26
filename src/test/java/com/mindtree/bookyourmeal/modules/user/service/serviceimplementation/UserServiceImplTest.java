package com.mindtree.bookyourmeal.modules.user.service.serviceimplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
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

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.dto.UserDto;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.entity.Role;
import com.mindtree.bookyourmeal.modules.core.entity.User;
import com.mindtree.bookyourmeal.modules.core.repository.UserRepository;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Category;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Ingredient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Nutrient;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.user.dto.EditUserDto;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;
import com.mindtree.bookyourmeal.modules.user.dto.UserInfoDto;
import com.mindtree.bookyourmeal.modules.user.entity.Cart;
import com.mindtree.bookyourmeal.modules.user.entity.FoodOrder;
import com.mindtree.bookyourmeal.modules.user.entity.FoodQuantity;
import com.mindtree.bookyourmeal.modules.user.entity.Suborder;
import com.mindtree.bookyourmeal.modules.user.exception.UserException;
import com.mindtree.bookyourmeal.modules.user.repository.CartRepository;
import com.mindtree.bookyourmeal.modules.user.repository.FoodOrderRepository;
import com.mindtree.bookyourmeal.modules.user.repository.FoodQuantityRepository;
import com.mindtree.bookyourmeal.modules.user.repository.SuborderRepository;
import com.mindtree.bookyourmeal.modules.user.service.UserService;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	@TestConfiguration
	static class UserServiceImplTestConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	UserService userService;
	@MockBean
	UserRepository userRepository;
	@MockBean
	CartRepository cartRepository;
	@MockBean
	FoodRepository foodRepository;
	@MockBean
	FoodQuantityRepository foodQuantityRepository;
	@MockBean
	FoodOrderRepository foodOrderRepository;
	@MockBean
	SuborderRepository suborderRepository;
	private User userWithCart;
	private User userWithoutCart, user, user1;
	private Cart cart;
	private Food food;
	private Food food2;
	private Food food3;
	private Restaurant restaurant;
	ModelMapper modelMapper = new ModelMapper();

	@Before
	public void setup() {
		Set<Food> foods = new HashSet<>();
		Set<Image> images = new HashSet<>();
		images.add(new Image("1", "Shah Ghouse"));
		MockitoAnnotations.initMocks(this);
		Address address = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		Role role = new Role(1, "USER", LocalDateTime.now());
		restaurant = new Restaurant(1, "Shah Ghouse", "Admin123", address, "shahghouse@mail.com", "230276", 3.8F, 1,
				foods, images, LocalDateTime.now());
		Set<Ingredient> ingredients = new HashSet<>();
		Nutrient nutrient = new Nutrient(1, 600, 7F, 2, 1.7F, 3.7F, LocalDateTime.now());
		ingredients.add(new Ingredient(1, "Mutton", 150, nutrient, LocalDateTime.now()));
		Category category = new Category(1, "Indian", true, LocalDateTime.now());
		Image foodImage = new Image("1", "DoPyaza");
		food = new Food(1, "Mutton Do Pyaza", "Slow braised lamb stew", 440, 250, 5.0F, nutrient, 4, category,
				restaurant, foodImage, true, ingredients, LocalDateTime.now());
		food2 = new Food(2, "Tandoori Roti", "Wheat flatbread", 440, 150, 5.0F, nutrient, 4, category, restaurant,
				foodImage, true, ingredients, LocalDateTime.now());
		food3 = new Food(3, "Trifle Pudding", "English dessert", 120, 150, 5.0F, nutrient, 4, category, restaurant,
				foodImage, true, ingredients, LocalDateTime.now());
		foods.add(food);
		foods.add(food2);
		restaurant.setFoods(foods);
		Set<FoodQuantity> foodQuantity = new HashSet<>();
		foodQuantity.add(new FoodQuantity(1, food, 1));
		foodQuantity.add(new FoodQuantity(1, food2, 2));
		cart = new Cart(1, foodQuantity, LocalDateTime.now());
		userWithCart = new User(1, "admin123", "Abdul", "9876543210", "abdul@mail.com", address, role, cart,
				LocalDateTime.now());
		userWithoutCart = new User(2, "admin123", "Ankit", "9876543211", "ankit@mail.com", address, role,
				LocalDateTime.now());
		user = new User(3, "axLbfYkxE6DkV8E07FsM5g==", "Jeevan", "8019263103", "jeevan@mail.com", address, role, cart,
				LocalDateTime.now());
		user1 = new User(3, "axLbfYkxE6DkV8E07FsM5g==", "seenu", "8912564321", "seenu1@mail.com", address, role, cart,
				LocalDateTime.now());
	}

	@Test
	public void getFoodsFromCartTest() {
		int userId = 1;
		Optional<User> userOptional = Optional.of(userWithCart);
		Optional<Cart> cartOptional = Optional.of(cart);
		Mockito.when(userRepository.findById(userId)).thenReturn(userOptional);
		Mockito.when(cartRepository.findById(userWithCart.getCart().getCartId())).thenReturn(cartOptional);
//	if (userOptional.isPresent()) {
//		userWithCart = userOptional.get();
//	}
//	if (userWithCart.getCart() != null) {
//		if (cartOptional.isPresent()) {
//			cart = cartOptional.get();
//		}
//	}
		Set<FoodsInCartDto> foodsInCartDtos = new HashSet<>();
		if (!cart.getFoodsInCart().isEmpty()) {
			for (FoodQuantity foodQuantityObj : cart.getFoodsInCart()) {
				FoodsInCartDto foodsInCartDtoObj = new FoodsInCartDto();
				foodsInCartDtoObj.setFoodId(foodQuantityObj.getFood().getFoodId());
				foodsInCartDtoObj.setFoodName(foodQuantityObj.getFood().getFoodName());
				foodsInCartDtoObj.setFoodPrice(foodQuantityObj.getFood().getFoodPrice());
				foodsInCartDtoObj.setAmount(foodQuantityObj.getFoodQuantity());
				foodsInCartDtoObj.setFoodDescription(foodQuantityObj.getFood().getFoodDescription());
				foodsInCartDtoObj.setImageId(foodQuantityObj.getFood().getFoodImage().getPictureId());
				foodsInCartDtoObj.setRestaurantName(foodQuantityObj.getFood().getRestaurant().getRestaurantName());
				foodsInCartDtos.add(foodsInCartDtoObj);
			}
		}
		assertEquals(foodsInCartDtos, userService.getFoodsFromCart(1));
	}

	@Test
	public void addFoodToCartTest() {
		int userId = 1;
		int foodId = 3;
		Optional<User> userOptional = Optional.of(userWithCart);
		Optional<Cart> cartOptional = Optional.of(cart);
		Optional<Food> foodOptional = Optional.of(food3);
		Mockito.when(userRepository.findById(userId)).thenReturn(userOptional);
		Mockito.when(cartRepository.findById(userWithCart.getCart().getCartId())).thenReturn(cartOptional);
		Mockito.when(foodRepository.findById(foodId)).thenReturn(foodOptional);
		Set<FoodQuantity> foodQuantitiesInCart = new HashSet<>();
		FoodQuantity foodQuantity3 = new FoodQuantity(3, food3, 1);
		foodQuantitiesInCart = cart.getFoodsInCart();
		foodQuantitiesInCart.add(foodQuantity3);
		Mockito.when(foodQuantityRepository.save(foodQuantity3)).thenReturn(foodQuantity3);
		Cart cart = cartOptional.get();
		cart.setFoodsInCart(foodQuantitiesInCart);
		Mockito.when(cartRepository.save(cart)).thenReturn(cart);
		Optional<FoodQuantity> foodQuantityOptional = Optional.of(foodQuantity3);
		Mockito.when(foodQuantityRepository.findById(3)).thenReturn(foodQuantityOptional);
		Set<FoodsInCartDto> foodsInCartDtos = new HashSet<>();
		if (!cart.getFoodsInCart().isEmpty()) {
			for (FoodQuantity foodQuantityObj : cart.getFoodsInCart()) {
				FoodsInCartDto foodsInCartDtoObj = new FoodsInCartDto();
				foodsInCartDtoObj.setFoodId(foodQuantityObj.getFood().getFoodId());
				foodsInCartDtoObj.setFoodName(foodQuantityObj.getFood().getFoodName());
				foodsInCartDtoObj.setFoodPrice(foodQuantityObj.getFood().getFoodPrice());
				foodsInCartDtoObj.setFoodDescription(foodQuantityObj.getFood().getFoodDescription());
				foodsInCartDtoObj.setAmount(foodQuantityObj.getFoodQuantity());
				foodsInCartDtoObj.setImageId(foodQuantityObj.getFood().getFoodImage().getPictureId());
				foodsInCartDtoObj.setRestaurantName(foodQuantityObj.getFood().getRestaurant().getRestaurantName());
				if (foodsInCartDtoObj.getFoodId() == foodId)
					foodsInCartDtoObj.setAmount(foodsInCartDtoObj.getAmount() + 1);
				foodsInCartDtos.add(foodsInCartDtoObj);
			}
		}
		assertEquals(foodsInCartDtos, userService.addFoodToCart(userId, foodId));
	}

	@Test
	public void removeFoodFromCartTest() {
		int userId = 1;
		int foodId = 2;
		int isFoodRemoved = 0;
		Optional<User> userOptional = Optional.of(userWithCart);
		Optional<Cart> cartOptional = Optional.of(cart);
		Optional<Food> foodOptional = Optional.of(food3);
		Mockito.when(userRepository.findById(userId)).thenReturn(userOptional);
		Mockito.when(cartRepository.findById(userWithCart.getCart().getCartId())).thenReturn(cartOptional);
		Mockito.when(foodRepository.findById(foodId)).thenReturn(foodOptional);
		Set<FoodQuantity> foodQuantitiesInCart = new HashSet<>();
		foodQuantitiesInCart = cart.getFoodsInCart();
		FoodQuantity updatedFoodQuantity = foodQuantitiesInCart.stream().filter(i -> i.getFood().getFoodId() == foodId)
				.collect(Collectors.toList()).get(0);
		Optional<FoodQuantity> foodQuantityOptional = Optional.of(updatedFoodQuantity);
		Mockito.when(foodQuantityRepository.findById(1)).thenReturn(foodQuantityOptional);
		foodQuantitiesInCart.remove(updatedFoodQuantity);
		updatedFoodQuantity.setFoodQuantity(updatedFoodQuantity.getFoodQuantity() - 1);
		foodQuantitiesInCart.add(updatedFoodQuantity);
		Mockito.when(foodQuantityRepository.save(updatedFoodQuantity)).thenReturn(updatedFoodQuantity);
		if (!updatedFoodQuantity.equals(null))
			isFoodRemoved = foodId;
		Set<FoodsInCartDto> foodsInCartDtos = new HashSet<>();
		if (!cart.getFoodsInCart().isEmpty()) {
			for (FoodQuantity foodQuantityObj : cart.getFoodsInCart()) {
				FoodsInCartDto foodsInCartDtoObj = new FoodsInCartDto();
				foodsInCartDtoObj.setFoodId(foodQuantityObj.getFood().getFoodId());
				foodsInCartDtoObj.setFoodName(foodQuantityObj.getFood().getFoodName());
				foodsInCartDtoObj.setFoodPrice(foodQuantityObj.getFood().getFoodPrice());
				foodsInCartDtoObj.setFoodDescription(foodQuantityObj.getFood().getFoodDescription());
				foodsInCartDtoObj.setAmount(foodQuantityObj.getFoodQuantity());
				foodsInCartDtoObj.setImageId(foodQuantityObj.getFood().getFoodImage().getPictureId());
				foodsInCartDtoObj.setRestaurantName(foodQuantityObj.getFood().getRestaurant().getRestaurantName());
				foodsInCartDtos.add(foodsInCartDtoObj);
			}
		}
		assertEquals(foodsInCartDtos, userService.removeFoodFromCart(userId, foodId));
	}

	@Test
	public void placeOrderTest() {
		FoodOrder foodOrder = new FoodOrder();
		Set<Suborder> subordersInTheOrder = new HashSet<>();
		List<FoodsInCartDto> foodsInCartDtos = new ArrayList<>();
		for (FoodQuantity foodQuantityObj : cart.getFoodsInCart()) {
			FoodsInCartDto foodsInCartDtoObj = new FoodsInCartDto();
			foodsInCartDtoObj.setFoodId(foodQuantityObj.getFood().getFoodId());
			foodsInCartDtoObj.setFoodName(foodQuantityObj.getFood().getFoodName());
			foodsInCartDtoObj.setFoodPrice(foodQuantityObj.getFood().getFoodPrice());
			foodsInCartDtoObj.setFoodDescription(foodQuantityObj.getFood().getFoodDescription());
			foodsInCartDtoObj.setAmount(foodQuantityObj.getFoodQuantity());
			foodsInCartDtoObj.setImageId(foodQuantityObj.getFood().getFoodImage().getPictureId());
			foodsInCartDtoObj.setRestaurantName(foodQuantityObj.getFood().getRestaurant().getRestaurantName());
			foodsInCartDtos.add(foodsInCartDtoObj);
		}
		subordersInTheOrder.add(new Suborder(1, 300F, true, restaurant.getFoods(), LocalDateTime.now()));
		subordersInTheOrder.add(new Suborder(2, 700F, true, new HashSet<>(Arrays.asList(food3)), LocalDateTime.now()));
		foodOrder = new FoodOrder(1, 1000F, true, userWithCart, subordersInTheOrder, LocalDateTime.now());
		List<Suborder> existingSuborders = new ArrayList<>();
		int i = 3;
		for (Suborder so : subordersInTheOrder) {
			existingSuborders.add(so);
			so.setSuborderId(i);
			i++;
		}
		Mockito.when(suborderRepository.findAll()).thenReturn(existingSuborders);
		List<Suborder> updatedListOfSuborders = existingSuborders;
		for (Suborder so : subordersInTheOrder) {
			updatedListOfSuborders.add(so);
		}
		List<FoodOrder> existingOrders = new ArrayList<>();
		existingOrders.add(foodOrder);
		boolean f = false;
		List<FoodOrder> updatedListOfOrders = existingOrders;
		foodOrder.setOrderId(2);
		updatedListOfOrders.add(foodOrder);
		Mockito.when(foodOrderRepository.findAll()).thenReturn(existingOrders).thenReturn(updatedListOfOrders);
		System.err.println(food);
		System.err.println(food2);
		System.err.println(food3);
		Optional<Food> food1Optional = Optional.of(food);
		Optional<Food> food2Optional = Optional.of(food2);
		Optional<Food> food3Optional = Optional.of(food3);
		Mockito.when(foodRepository.findById(1)).thenReturn(food1Optional);
		Mockito.when(foodRepository.findById(2)).thenReturn(food2Optional);
		Mockito.when(foodRepository.findById(3)).thenReturn(food3Optional);
		Mockito.when(suborderRepository.saveAll(existingSuborders)).thenReturn(updatedListOfSuborders);
		Mockito.when(foodOrderRepository.saveAll(existingOrders)).thenReturn(updatedListOfOrders);
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(userWithCart));
		assertEquals(0, userService.placeOrder(1, foodsInCartDtos.stream().collect(Collectors.toSet())));
	}

	@Test
	public void loginUserTest() throws ServiceException {
		String mail = "jeevan@mail.com";
		String password = "Jeevan@123";
		Mockito.when(userRepository.findByMailId(mail)).thenReturn(Optional.of(user));
		UserInfoDto userFromTest = modelMapper.map(user, UserInfoDto.class);
		UserInfoDto userFromService = userService.loginUser(mail, password);
		assertEquals(userFromService.getUserName(), userFromTest.getUserName());
		assertEquals(userFromService.getMailId(), userFromTest.getMailId());
		assertEquals(userFromService.getMobileNumber(), userFromTest.getMobileNumber());
		assertEquals(userFromService.getUserId(), userFromTest.getUserId());
		assertEquals(userFromService.getMailId(), userFromTest.getMailId());
	}

	@Test
	public void registerUserTest() throws ServiceException {
		Set<FoodOrder> foodOrder = new HashSet<>();
		foodOrder.add(new FoodOrder(1, 100, false, user1, null, LocalDateTime.now()));
		foodOrder.add(new FoodOrder(2, 150, false, user1, null, LocalDateTime.now()));
		Mockito.when(userRepository.findByMailId(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(userRepository.findByMobileNumber(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
		UserDto userDto = modelMapper.map(user1, UserDto.class);
		userDto.setUserPassword("Jeevan@123");
		UserInfoDto userFromTest = modelMapper.map(user1, UserInfoDto.class);
		UserInfoDto userFromService = userService.registerUser(userDto);
		assertEquals(userFromService.getUserName(), userFromTest.getUserName());
		assertEquals(userFromService.getMailId(), userFromTest.getMailId());
		assertEquals(userFromService.getMobileNumber(), userFromTest.getMobileNumber());
		assertEquals(userFromService.getUserId(), userFromTest.getUserId());
		assertEquals(userFromService.getMailId(), userFromTest.getMailId());
	}

	@Test
	public void googleLoginTest() throws UserException {
		Set<FoodOrder> foodOrder = new HashSet<>();
		foodOrder.add(new FoodOrder(1, 100, false, user1, null, LocalDateTime.now()));
		foodOrder.add(new FoodOrder(2, 150, false, user1, null, LocalDateTime.now()));
		Mockito.when(userRepository.findByMailId(user1.getMailId())).thenReturn(Optional.of(user1));
		UserDto userDto = modelMapper.map(user1, UserDto.class);
		userDto.setUserPassword("Jeevan@123");
		userDto.setPastOrders(foodOrder);
		userDto.setRole(new Role(1, "USER", LocalDateTime.now()));
		UserInfoDto userFromTest = modelMapper.map(user1, UserInfoDto.class);
		UserInfoDto userFromService = userService.googleLogin(userDto);
		assertEquals(userFromService.getUserName(), userFromTest.getUserName());
		assertEquals(userFromService.getMailId(), userFromTest.getMailId());
		assertEquals(userFromService.getMobileNumber(), userFromTest.getMobileNumber());
		assertEquals(userFromService.getUserId(), userFromTest.getUserId());
		assertEquals(userFromService.getMailId(), userFromTest.getMailId());

	}

	@Test
	public void getEditUserProfile() {

		Mockito.when(userRepository.getOne(1)).thenReturn(userWithCart);
		System.out.println("USER " + userWithCart.getUserName());
		EditUserDto edituserDto = modelMapper.map(userWithCart, EditUserDto.class);
		System.out.println("EDITUSER " + edituserDto.getUserName());
		assertEquals(edituserDto.getUserName(), userService.getEditUserProfile(1).getUserName());
		assertEquals(edituserDto.getUserPassword(), userService.getEditUserProfile(1).getUserPassword());
		assertEquals(edituserDto.getMobileNumber(), userService.getEditUserProfile(1).getMobileNumber());
		assertEquals(edituserDto.getMailId(), userService.getEditUserProfile(1).getMailId());
		assertEquals(edituserDto.getAddress().getCity(), userService.getEditUserProfile(1).getAddress().getCity());
		assertEquals(edituserDto.getAddress().getState(), userService.getEditUserProfile(1).getAddress().getState());
		assertEquals(edituserDto.getAddress().getDoorNumber(),
				userService.getEditUserProfile(1).getAddress().getDoorNumber());
		assertEquals(edituserDto.getAddress().getLocality(),
				userService.getEditUserProfile(1).getAddress().getLocality());
		assertEquals(edituserDto.getAddress().getStreetName(),
				userService.getEditUserProfile(1).getAddress().getStreetName());
		assertEquals(edituserDto.getAddress().getZipcode(),
				userService.getEditUserProfile(1).getAddress().getZipcode());
	}

	@Test
	public void editUserProfile() throws UserException {
		Optional<User> userOptional = Optional.of(userWithCart);
		Mockito.when(userRepository.findById(1)).thenReturn(userOptional);
		Mockito.when(userRepository.save(userWithCart)).thenReturn(userWithCart);
		EditUserDto edituserDto = modelMapper.map(userWithCart, EditUserDto.class);
		assertEquals(edituserDto.getUserName(), userService.editUserProfile(edituserDto).getUserName());
	}
}
