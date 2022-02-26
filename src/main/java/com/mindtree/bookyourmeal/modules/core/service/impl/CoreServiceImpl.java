  package com.mindtree.bookyourmeal.modules.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bookyourmeal.modules.core.dto.FoodRestaurantSearchDto;
import com.mindtree.bookyourmeal.modules.core.dto.RestaurantWithoutFoodsDto;
import com.mindtree.bookyourmeal.modules.core.dto.SearchDto;
import com.mindtree.bookyourmeal.modules.core.entity.FoodRestaurantSearch;
import com.mindtree.bookyourmeal.modules.core.exception.CoreException;
import com.mindtree.bookyourmeal.modules.core.exception.NoFoodFoundException;
import com.mindtree.bookyourmeal.modules.core.repository.CoreRepository;
import com.mindtree.bookyourmeal.modules.core.repository.SearchRepository;
import com.mindtree.bookyourmeal.modules.core.service.CoreService;
import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;
import com.mindtree.bookyourmeal.modules.restaurant.repository.FoodRepository;
import com.mindtree.bookyourmeal.modules.restaurant.repository.RestaurantRepository;
import com.mindtree.bookyourmeal.modules.user.repository.SuborderRepository;

@Service
public class CoreServiceImpl implements CoreService {

	@Autowired
	FoodRepository foodrepository;

	@Autowired
	SuborderRepository subOrderRepos;

	@Autowired
	SearchRepository searchrepository;

	@Autowired

	RestaurantRepository restaurantrepository;
	@Autowired
	CoreRepository coreRepository;

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

	@Override
	public List<FoodDto> getallfoods() {
		List<Food> foodlist = foodrepository.findAll();
		return foodlist.stream().map(e -> convertFoodEntityToDto(e)).collect(Collectors.toList());

	}

	@Override
	public List<Object> fetchTheRecommendedFood() {
		List<Object> objects = subOrderRepos.getMaximumOrderFood();
		for (int i = 0; i < objects.size(); i++) {
			System.out.println(objects.get(i));
		}

		return objects;
	}

	@Override

	public List<Object> fetchRestaurantLocation() {
		return restaurantrepository.getAllLocationOfRestaurant();
	}

	public List<SearchDto> searchFood(String searchKey) {
		List<SearchDto> l = new ArrayList<>();
		List<Object[]> foodsSearched = coreRepository.searchFoods(searchKey);
		for (Object[] obj : foodsSearched) {
			SearchDto sdto = new SearchDto(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]),
					String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5]), String.valueOf(obj[6]),
					String.valueOf(obj[7]), String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
					String.valueOf(obj[11]), String.valueOf(obj[12]));
			l.add(sdto);
		}
		return l;

	}

	@Override
	public FoodDto getfoodbyfoodid(int foodId) {
		Food food = foodrepository.getOne(foodId);
		return convertFoodEntityToDto(food);
	}

	@Override
	public FoodRestaurantSearchDto searchbykeyword(String searchedfood) throws CoreException {
		// TODO Auto-generated method stub
		FoodRestaurantSearchDto foodrestaurantsearchdto=new FoodRestaurantSearchDto();
		FoodRestaurantSearch result=searchrepository.searchbykeyword(searchedfood);
		List<FoodDto> foodlist=result.getFoods().stream().filter(e->e.isFoodAvailabilityStatus()).map(e->convertFoodEntityToDto(e)).collect(Collectors.toList());
		List<RestaurantWithoutFoodsDto> restaurantlist=result.getRestaurants().stream().filter(e->e.getRestaurantStatus()==1).map(e->convertRestaurantEntityToResWithoutFoodsDto(e)).collect(Collectors.toList());
		if(foodlist.isEmpty() && restaurantlist.isEmpty())
			throw new NoFoodFoundException("No matching results found, try another keywords....");
		foodrestaurantsearchdto.setFoods(foodlist);
		foodrestaurantsearchdto.setRestaurants(restaurantlist);
		System.out.println(foodrestaurantsearchdto);
		return foodrestaurantsearchdto;
		
	}

}