package com.mindtree.bookyourmeal.modules.user.service.serviceimplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.dto.UserDto;
import com.mindtree.bookyourmeal.modules.core.entity.User;
import com.mindtree.bookyourmeal.modules.core.repository.RoleRepository;
import com.mindtree.bookyourmeal.modules.core.repository.UserRepository;
import com.mindtree.bookyourmeal.modules.core.util.AES;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.user.dto.EditUserDto;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;
import com.mindtree.bookyourmeal.modules.user.dto.UserInfoDto;
import com.mindtree.bookyourmeal.modules.user.entity.Cart;
import com.mindtree.bookyourmeal.modules.user.entity.FoodOrder;
import com.mindtree.bookyourmeal.modules.user.entity.FoodQuantity;
import com.mindtree.bookyourmeal.modules.user.entity.Suborder;
import com.mindtree.bookyourmeal.modules.user.exception.EmailAlreadyExistsException;
import com.mindtree.bookyourmeal.modules.user.exception.GoogleLoginException;
import com.mindtree.bookyourmeal.modules.user.exception.IncorrectPasswordException;
import com.mindtree.bookyourmeal.modules.user.exception.InvalidEmailException;
import com.mindtree.bookyourmeal.modules.user.exception.InvalidNumberException;
import com.mindtree.bookyourmeal.modules.user.exception.InvalidZipcodeException;
import com.mindtree.bookyourmeal.modules.user.exception.PhoneNumberAlreadyExistsException;
import com.mindtree.bookyourmeal.modules.user.exception.UserException;
import com.mindtree.bookyourmeal.modules.user.exception.UserNotFoundException;
import com.mindtree.bookyourmeal.modules.user.repository.CartRepository;
import com.mindtree.bookyourmeal.modules.user.repository.FoodOrderRepository;
import com.mindtree.bookyourmeal.modules.user.repository.FoodQuantityRepository;
import com.mindtree.bookyourmeal.modules.user.repository.SuborderRepository;
import com.mindtree.bookyourmeal.modules.user.service.UserService;

class MyComp implements Comparator<FoodsInCartDto> {

	@Override
	public int compare(FoodsInCartDto arg0, FoodsInCartDto arg1) {
		return arg0.getRestaurantName().compareTo(arg1.getRestaurantName());
	}

}

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	FoodRepository foodRepository;
	@Autowired
	FoodQuantityRepository foodQuantityRepository;
	@Autowired
	FoodOrderRepository foodOrderRepository;
	@Autowired
	SuborderRepository suborderRepository;
	RoleRepository roleRepository;
	ModelMapper modelMapper = new ModelMapper();
	AES encryption;

	@Override
	public Set<FoodsInCartDto> addFoodToCart(int userId, int foodId) {
		User user = new User();
		Food food = new Food();
		Cart cart = new Cart();
		FoodQuantity foodQuantity = new FoodQuantity();
		Set<FoodQuantity> foodsQuantity = new HashSet<>();
		Optional<User> currentUser = userRepository.findById(userId);
		if (currentUser.isPresent()) {
			user = currentUser.get();
		}
		Optional<Food> currentFood = foodRepository.findById(foodId);
		if (currentFood.isPresent()) {
			food = currentFood.get();
			foodQuantity.setFood(food);
		}

		if (user.getCart() != null) {

			Optional<Cart> currentCart = cartRepository.findById(user.getCart().getCartId());

			////////////////////////////

			if (currentCart.isPresent()) {
				cart = currentCart.get();
				Set<FoodQuantity> foodQuantitiesInCart = new HashSet<>();

				foodQuantitiesInCart = cart.getFoodsInCart();

				int c = 0;
				Iterator<FoodQuantity> it = foodQuantitiesInCart.iterator();
				while (it.hasNext()) {
					FoodQuantity foodQuantityObj = it.next();
					if (foodQuantityObj.getFood().equals(food)) {

						FoodQuantity updatedFoodQuantity = foodQuantityRepository
								.findById(foodQuantityObj.getFoodQuantityId()).get();
						updatedFoodQuantity.setFoodQuantity(updatedFoodQuantity.getFoodQuantity() + 1);
						foodsQuantity.add(foodQuantityRepository.save(updatedFoodQuantity));
					} else

					{
						c++;
						foodsQuantity.add(foodQuantityObj);
					}
				}
				if (foodQuantitiesInCart.size() == c || foodQuantitiesInCart.size() == 0) {
					foodQuantity.setFoodQuantity(1);
					foodsQuantity.add(foodQuantity);
					cart.setFoodsInCart(foodsQuantity);
					foodQuantityRepository.save(foodQuantity);
				}

			}
		} else {
			foodQuantity.setFoodQuantity(1);
			foodsQuantity.add(foodQuantity);
			cart.setFoodsInCart(foodsQuantity);
			foodQuantityRepository.save(foodQuantity);
			cartRepository.save(cart);
			user.setCart(cart);
			userRepository.save(user);
		}
		cart.setFoodsInCart(foodsQuantity);
		cartRepository.save(cart);
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
		return foodsInCartDtos;
	}

	@Override
	public Set<FoodsInCartDto> removeFoodFromCart(int userId, int foodId) {
		User user = new User();
		Food food = new Food();
		Cart cart = new Cart();
		FoodQuantity foodQuantity = new FoodQuantity();
		Set<FoodQuantity> foodsQuantity = new HashSet<>();
		int isFoodRemoved = 0;
		Optional<User> currentUser = userRepository.findById(userId);
		if (currentUser.isPresent()) {
			user = currentUser.get();
		}
		Optional<Food> currentFood = foodRepository.findById(foodId);
		if (currentFood.isPresent()) {
			food = currentFood.get();
			foodQuantity.setFood(food);
		}

		if (user.getCart() != null) {

			Optional<Cart> currentCart = cartRepository.findById(user.getCart().getCartId());

			////////////////////////////

			if (currentCart.isPresent()) {
				cart = currentCart.get();
				Set<FoodQuantity> foodQuantitiesInCart = new HashSet<>();

				foodQuantitiesInCart = cart.getFoodsInCart();

				Iterator<FoodQuantity> it = foodQuantitiesInCart.iterator();
				while (it.hasNext()) {
					FoodQuantity foodQuantityObj = it.next();
					if (foodQuantityObj.getFood().equals(food)) {
						if (foodQuantityObj.getFoodQuantity() >= 2) {
							FoodQuantity updatedFoodQuantity = foodQuantityRepository
									.findById(foodQuantityObj.getFoodQuantityId()).get();
							updatedFoodQuantity.setFoodQuantity(updatedFoodQuantity.getFoodQuantity() - 1);
							foodsQuantity.add(foodQuantityRepository.save(updatedFoodQuantity));
						} else {
							isFoodRemoved = foodQuantityObj.getFoodQuantityId();
						}
					} else {
						foodsQuantity.add(foodQuantityObj);
					}
				}
			}
		}
		cart.setFoodsInCart(foodsQuantity);
		cartRepository.save(cart);
		if (isFoodRemoved != 0)
			foodQuantityRepository.deleteById(isFoodRemoved);
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
		return foodsInCartDtos;
	}

	public Set<FoodsInCartDto> getFoodsFromCart(int userId) {
		User user = new User();
		Cart cart = new Cart();
		Optional<User> currentUser = userRepository.findById(userId);
		if (currentUser.isPresent()) {
			user = currentUser.get();
		}
		if (user.getCart() != null) {

			Optional<Cart> currentCart = cartRepository.findById(user.getCart().getCartId());

			////////////////////////////

			if (currentCart.isPresent()) {
				cart = currentCart.get();
			}
		}
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
		return foodsInCartDtos;
	}

	@Override
	public Integer placeOrder(int userId, Set<FoodsInCartDto> foodsOrdered) {
		FoodOrder foodOrder = new FoodOrder();
		Set<Suborder> subordersInTheOrder = new HashSet<>();
		List<FoodsInCartDto> foods = new ArrayList<>();
		List<Suborder> allSubOrders = suborderRepository.findAll();
		List<FoodOrder> allOrders = foodOrderRepository.findAll();
		Set<Food> foodsInThisRestaurantFetched;
		for (FoodsInCartDto f : foodsOrdered) {
			foods.add(f);
		}
		Collections.sort(foods, new MyComp());
		Set<String> resNames = foodsOrdered.stream().map(food -> food.getRestaurantName()).collect(Collectors.toSet());
		for (String restaurant : resNames) {
			List<FoodsInCartDto> foodsInThisRestaurant = foods.stream()
					.filter(f -> f.getRestaurantName().equals(restaurant)).collect(Collectors.toList());
			foodsInThisRestaurantFetched = new HashSet<>();
			for (FoodsInCartDto food : foodsInThisRestaurant) {
				foodsInThisRestaurantFetched.add(foodRepository.findById(food.getFoodId()).get());
			}
			Suborder suborder = new Suborder();
			suborder.setFoodsOrdered(foodsInThisRestaurantFetched);
			subordersInTheOrder.add(suborder);
			allSubOrders.add(suborder);
		}
		suborderRepository.saveAll(allSubOrders);
		User user = userRepository.findById(userId).get();
		foodOrder.setUser(user);
		foodOrder.setSuborders(subordersInTheOrder);
		allOrders.add(foodOrder);
		foodOrderRepository.saveAll(allOrders);
		foodQuantityRepository.deleteAll(user.getCart().getFoodsInCart());
		return foodOrderRepository.findAll().get(allOrders.size() - 1).getOrderId();
	}

	private User convertUserDtoToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

	private User convertEdiUserDtoToEntity(EditUserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

	private EditUserDto convertEdiUserDtoToEntity(User user) {
		return modelMapper.map(user, EditUserDto.class);
	}

	private UserInfoDto convertUserToUserInfoEntity(User user) {
		return modelMapper.map(user, UserInfoDto.class);
	}

	// User Registration

	public UserInfoDto registerUser(UserDto userDto) throws ServiceException {
		User user = convertUserDtoToEntity(userDto);
		if (validateEmail(user.getMailId()) && validatePassword(user.getUserPassword())
				&& validateNumber(user.getMobileNumber()) && validateInput(user.getUserName())
				&& validateInput(user.getAddress().getLocality()) && validateInput(user.getAddress().getCity())
				&& validateInput(user.getAddress().getState()) && validateZipcode(user.getAddress().getZipcode())) {
			Optional<User> optionUser = userRepository.findByMailId(user.getMailId());
			if (optionUser.isPresent())
				throw new EmailAlreadyExistsException("Email already Exists");
			optionUser = userRepository.findByMobileNumber(user.getMobileNumber());
			if (optionUser.isPresent())
				throw new PhoneNumberAlreadyExistsException("Number already Exists");
//			Optional<Role> roleOptional = roleRepository.findById(user.getRole().getRoleId());
//			roleOptional.orElseThrow(() -> new InvalidRoleException("Invalid Role"));
//			user.setRole(roleOptional.get());
			String str = AES.encrypt(user.getUserPassword());
			user.setUserPassword(str);

			return convertUserToUserInfoEntity(userRepository.save(user));
		}
		throw new UserException("Invalid mailid or password");
	}

	public boolean validateEmail(String email) throws InvalidEmailException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		if (email != null && pattern.matcher(email).matches())
			return true;
		throw new InvalidEmailException("Enter valid Email");

	}

	public boolean validateNumber(String number) throws InvalidNumberException {
		String numberRegex = "[7-9][0-9]*{9}";
		Pattern pattern = Pattern.compile(numberRegex);
		if (number != null && pattern.matcher(number).matches())
			return true;
		throw new InvalidNumberException("Enter Valid Number");
	}

	public boolean validateZipcode(String zipcode) throws InvalidZipcodeException {
		String zipcodeRegex = "[0-9]{6}";
		Pattern pattern = Pattern.compile(zipcodeRegex);
		if (zipcode != null && pattern.matcher(zipcode).matches())
			return true;
		throw new InvalidZipcodeException("Enter Valid Zipcode");

	}

	public boolean validateInput(String name) throws InvalidNumberException {
		String nameRegex = "[a-zA-Z0-9][a-zA-Z0-9',-/ ]*";
		Pattern pattern = Pattern.compile(nameRegex);
		if (name != null && pattern.matcher(name).matches())
			return true;
		throw new InvalidNumberException("Enter Valid Input ");

	}

	public boolean validatePassword(String password) throws InvalidNumberException {
		String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{7,}";
		Pattern pattern = Pattern.compile(passwordRegex);
		if (password != null && pattern.matcher(password).matches())
			return true;
		throw new InvalidNumberException("Enter Valid Password");
	}

	public UserInfoDto loginUser(String mailId, String password) throws UserException {
		if (validateEmail(mailId) && validatePassword(password)) {
			Optional<User> optionalUser = userRepository.findByMailId(mailId);
			optionalUser.orElseThrow(() -> new UserNotFoundException("Not Valid Credentials"));
			User user = optionalUser.get();
			System.err.println(mailId + " " + user.getMailId());
			if (user.getUserPassword() == null) {
				throw new GoogleLoginException("Please Login with Google");
			}
			String str = AES.decrypt(user.getUserPassword());
			if (password.equals(str)) {
				return convertUserToUserInfoEntity(user);
			}
			throw new IncorrectPasswordException("Enter Correct Password");

		} else
			throw new UserException("Invalid mailId or password");
	}

	public UserInfoDto googleLogin(UserDto userDto) throws UserException {
		if (validateEmail(userDto.getMailId()) && validateInput(userDto.getUserName())) {
			User user = convertUserDtoToEntity(userDto);
			Optional<User> optionalUser = userRepository.findByMailId(user.getMailId());
			if (optionalUser.isPresent())
				return convertUserToUserInfoEntity(optionalUser.get());
			else
				return convertUserToUserInfoEntity(userRepository.save(user));
		} else
			throw new UserException("Invalid username or password");
	}

	@Override
	public EditUserDto editUserProfile(EditUserDto userDto) throws UserException {

		User user = userRepository.findById(userDto.getUserId()).get();
		User editUser = convertEdiUserDtoToEntity(userDto);
		if (!editUser.getUserName().equals("")) {
			user.setUserName(editUser.getUserName());
		} else {
			user.setUserName(user.getUserName());
		}

		if (!editUser.getMailId().equals("") && validateEmail(editUser.getMailId())) {
			user.setMailId(editUser.getMailId());
		} else if (validateEmail(user.getMailId())) {
			user.setMailId(user.getMailId());
		}

		if (!editUser.getMobileNumber().equals("") && validateNumber(editUser.getMobileNumber())) {
			user.setMobileNumber(editUser.getMobileNumber());
		} else if (validateNumber(user.getMobileNumber())) {
			user.setMobileNumber(user.getMobileNumber());
		}

		if (!editUser.getAddress().getDoorNumber().equals("")) {
			user.getAddress().setDoorNumber(editUser.getAddress().getDoorNumber());
		} else {
			user.getAddress().setDoorNumber(user.getAddress().getDoorNumber());
		}
		if (!editUser.getAddress().getLocality().equals("")) {
			user.getAddress().setLocality(editUser.getAddress().getLocality());
		} else {
			user.getAddress().setLocality(user.getAddress().getLocality());
		}
		if (!editUser.getAddress().getStreetName().equals("")) {
			user.getAddress().setStreetName(editUser.getAddress().getStreetName());
		} else {
			user.getAddress().setStreetName(user.getAddress().getStreetName());
		}
		if (!editUser.getAddress().getCity().equals("")) {
			user.getAddress().setCity(editUser.getAddress().getCity());
		} else {
			user.getAddress().setCity(user.getAddress().getCity());
		}
		if (!editUser.getAddress().getState().equals("")) {
			user.getAddress().setState(editUser.getAddress().getState());
		} else {
			user.getAddress().setState(user.getAddress().getState());
		}
		if (!editUser.getAddress().getZipcode().equals("")) {
			user.getAddress().setZipcode(editUser.getAddress().getZipcode());
		} else {
			user.getAddress().setZipcode(user.getAddress().getZipcode());
		}
		User saveduserObj = userRepository.save(user);

		if (saveduserObj != null) {
			return modelMapper.map(saveduserObj, EditUserDto.class);
		} else {
			return null;
		}
	}

	@Override
	public EditUserDto getEditUserProfile(int userId) {
		User user = userRepository.getOne(userId);
		return convertEdiUserDtoToEntity(user);
	}

}
