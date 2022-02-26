package com.mindtree.bookyourmeal.modules.user.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.dto.UserDto;
import com.mindtree.bookyourmeal.modules.user.dto.EditUserDto;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;
import com.mindtree.bookyourmeal.modules.user.dto.UserInfoDto;
import com.mindtree.bookyourmeal.modules.user.exception.UserException;
import com.mindtree.bookyourmeal.modules.user.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/getfoodsfromcart")
	public ResponseEntity<Set<FoodsInCartDto>> getFoodsFromCart(@RequestParam("userid") int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getFoodsFromCart(userId));
	}

	@PostMapping("/addfoodtocart")
	public ResponseEntity<Set<FoodsInCartDto>> addFoodToCart(@RequestParam("userid") int userId,
			@RequestParam("foodid") int foodId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.addFoodToCart(userId, foodId));
	}

	@PostMapping("/removefoodfromcart")
	public ResponseEntity<Set<FoodsInCartDto>> removeFoodFromCart(@RequestParam("userid") int userId,
			@RequestParam("foodid") int foodId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.removeFoodFromCart(userId, foodId));
	}

	@PostMapping("/placeorder")
	public ResponseEntity<Integer> placeOrder(@RequestParam("userid") int userId,@RequestBody Set<FoodsInCartDto> foodsOrdered)
	{
		System.err.println(foodsOrdered);
		return ResponseEntity.status(HttpStatus.OK).body(userService.placeOrder(userId,foodsOrdered));
	}
	
	@PostMapping("/register-user")
	public ResponseEntity<UserInfoDto> registerUser(@RequestBody UserDto userDto) throws ServiceException {
		System.err.println("hit register");
		System.err.println(userDto);
		return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(userDto));
		
	}

	@GetMapping("/login-user")
	public ResponseEntity<UserInfoDto> loginUser(@RequestParam String mailId,@RequestParam String password) throws UserException {
		System.err.println("hit login");
		return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(mailId,password));
	}

	@PostMapping("/google-login")
	public ResponseEntity<UserInfoDto> googlelogin(@RequestBody UserDto user) throws UserException {
		System.err.println("google");
		return ResponseEntity.status(HttpStatus.OK).body(userService.googleLogin(user));
	}

	@PostMapping("/editUserProfile")
	public ResponseEntity<EditUserDto> editUserProfile(@RequestBody EditUserDto user) throws UserException{
		HttpHeaders header = new HttpHeaders();
		header.add("name", "successfully edited");
		return ResponseEntity.accepted().headers(header).body(userService.editUserProfile(user));
	}
	

	@GetMapping("/getEditUserProfileDetails")
	public ResponseEntity<EditUserDto> getEditUserProfileDetails(@RequestParam int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getEditUserProfile(userId));
	}

}
