package com.mindtree.bookyourmeal.modules.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bookyourmeal.modules.admin.dto.StatisticsDto;
import com.mindtree.bookyourmeal.modules.admin.exception.AdminException;
import com.mindtree.bookyourmeal.modules.admin.service.AdminService;
import com.mindtree.bookyourmeal.modules.core.dto.ExceptionDto;
import com.mindtree.bookyourmeal.modules.restaurant.dto.RestaurantInfoDto;

@CrossOrigin
@RestController
public class AdminController {
	@Autowired
	AdminService adminservice;

	@GetMapping("/getStatisticsCount")
	public ResponseEntity<StatisticsDto> getTheStatisticsRepost() {
		return ResponseEntity.status(HttpStatus.OK).body(adminservice.getStatisticsCount());
	}

	@GetMapping("/getTopTenFoods")
	public ResponseEntity<List<Object>> getTopTenFood() {
		return ResponseEntity.status(HttpStatus.OK).body(adminservice.getTopTenMaximumOrderedFood());
	}

	@GetMapping("/getTopFiveRestaurant")
	public ResponseEntity<List<Object>> getTopFiveRestaurant() {
		return ResponseEntity.status(HttpStatus.OK).body(adminservice.getTopFiverestaurant());
	}

	@GetMapping("/getLeastFiveRestaurant")
	public ResponseEntity<List<Object>> getTopLeastRestaurant() {
		return ResponseEntity.status(HttpStatus.OK).body(adminservice.getLeastFiverestaurant());
	}

	@GetMapping("/getRestaurantStatus")
	private ResponseEntity<List<RestaurantInfoDto>> allRestaurantStatus(@RequestParam int status) {
		return ResponseEntity.status(HttpStatus.OK).body(adminservice.allRestaurantStatus(status));
	}

	@PostMapping("/changeRestaurantStatus")
	private ResponseEntity<ExceptionDto> changeRestaurantStatus(@RequestParam int restaurantId,
			@RequestParam int request) throws AdminException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ExceptionDto(adminservice.changeRestaurantStatus(restaurantId, request)));
	}
}
