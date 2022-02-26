package com.mindtree.bookyourmeal.modules.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.restaurant.dto.EditFoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantException;
import com.mindtree.bookyourmeal.modules.restaurant.service.RestaurantService;

@RestController
@CrossOrigin
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@PostMapping("/register-restaurant")
	public ResponseEntity<RestaurantInfoDto> registerRestaurant(@RequestBody RestaurantDto restaurant)
			throws RestaurantException {
		System.err.println("CONTROLLER" + restaurant);
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.registerRestaurant(restaurant));
	}

	@GetMapping("/login-restaurant")
	public ResponseEntity<RestaurantInfoDto> loginRestaurant(@RequestParam String mailId, @RequestParam String password)
			throws RestaurantException {
		System.err.println("login restaurant");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.loginRestaurant(mailId, password));
	}

	@PostMapping("/food")
	public ResponseEntity<FoodDto> addFood(@RequestBody FoodDto food) throws ServiceException {
		System.out.println("fooddes");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.addFood(food));
	}

	@GetMapping("/fooditems")
	public ResponseEntity<List<EditFoodDto>> getFood(@RequestParam int restaurantId) throws ServiceException {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getFood(restaurantId));
	}

	@GetMapping("/updatefooditem")
	public ResponseEntity<FoodDto> updateFood(@RequestParam int foodid, @RequestParam int availibilityStatus)
			throws ServiceException {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.updateFood(foodid, availibilityStatus));

	}

	@GetMapping("/getrestaurantbyrestaurantid")
	public ResponseEntity<RestaurantDto> getrestaurantbyrestaurantid(@RequestParam int restaurantId)
			throws RestaurantException {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getrestaurantbyrestaurantid(restaurantId));
	}

	@GetMapping("/fetchRestaurantByCities")
	public ResponseEntity<Object> fetchRestaurantByCities(@RequestParam String city) {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.fetchRestaurantByCity(city));
	}
}
