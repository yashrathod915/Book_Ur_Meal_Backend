package com.mindtree.bookyourmeal.modules.core.controller;

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
import com.mindtree.bookyourmeal.modules.core.dto.FoodRestaurantSearchDto;
import com.mindtree.bookyourmeal.modules.core.dto.SearchDto;
import com.mindtree.bookyourmeal.modules.core.service.CoreService;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantException;

@CrossOrigin
@RestController
public class CoreController {

	@Autowired
	CoreService coreService;


	@GetMapping("/searchfood")
	public ResponseEntity<List<SearchDto>> searchfoods(@RequestParam("search") String searchKey) {
		return ResponseEntity.status(HttpStatus.OK).body(coreService.searchFood(searchKey));
	}

    
    @GetMapping("/getfoodbyfoodid")
	public ResponseEntity<FoodDto> getfoodbyfoodid(@RequestParam int foodId){
		return ResponseEntity.status(HttpStatus.OK).body(coreService.getfoodbyfoodid(foodId));
	}
    
	// FetchPopularFoodItems
	@GetMapping("/fetchTheCommendedFood")
	public ResponseEntity<List<Object>> getRecommendedFood() {
		return ResponseEntity.status(HttpStatus.OK).body(coreService.fetchTheRecommendedFood());
	}

	// FetchTheRestaurantLocation
	@GetMapping("/fetchTheResaturantLocation")
	public ResponseEntity<List<Object>> getTheRestaurantLocations() {
		return ResponseEntity.status(HttpStatus.OK).body(coreService.fetchRestaurantLocation());
	}
	
	@GetMapping("/searchbykeyword")
	private ResponseEntity<FoodRestaurantSearchDto> searchbykeyword(@RequestParam String searchedfood) throws ServiceException {
		FoodRestaurantSearchDto data = coreService.searchbykeyword(searchedfood);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

}




//@Autowired
//FoodRepository fr;
//@RequestMapping("/getFoods")
//public List getFood() {
//	return fr.getFood();
//}
