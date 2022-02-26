package com.mindtree.bookyourmeal.modules.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
@EnableJpaRepositories
public interface FoodRepository extends JpaRepository<Food, Integer> {

//		@Query(value ="select * from food where nutrient_score=8 limit 2",nativeQuery=true)
//		public List getFood();

	@Query(value = "select food_name from food f where f.restaurant_restaurant_id=?1", nativeQuery = true)
	List<String> getFoodNameList(int restaurantId);

}
