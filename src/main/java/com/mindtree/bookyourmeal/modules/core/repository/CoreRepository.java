package com.mindtree.bookyourmeal.modules.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;

@Repository
public interface CoreRepository extends JpaRepository<Food, Integer>{
@Query(value = "SELECT ankit_bookyourmeal_oct_19_dev.food.food_id,ankit_bookyourmeal_oct_19_dev.food.food_name,ankit_bookyourmeal_oct_19_dev.food.food_description,ankit_bookyourmeal_oct_19_dev.food.food_price, ankit_bookyourmeal_oct_19_dev.food.nutrient_score, ankit_bookyourmeal_oct_19_dev.food.food_rating, ankit_bookyourmeal_oct_19_dev.food.food_availability_status,ankit_bookyourmeal_oct_19_dev.image.picture_id, ankit_bookyourmeal_oct_19_dev.image.data, ankit_bookyourmeal_oct_19_dev.restaurant.restaurant_id, ankit_bookyourmeal_oct_19_dev.restaurant.restaurant_name, ankit_bookyourmeal_oct_19_dev.restaurant.restaurant_rating , ankit_bookyourmeal_oct_19_dev.restaurant.restaurant_status  FROM ankit_bookyourmeal_oct_19_dev.food INNER JOIN ankit_bookyourmeal_oct_19_dev.restaurant ON ankit_bookyourmeal_oct_19_dev.restaurant.restaurant_id=ankit_bookyourmeal_oct_19_dev.food.restaurant_restaurant_id INNER JOIN ankit_bookyourmeal_oct_19_dev.image ON ankit_bookyourmeal_oct_19_dev.food.food_image_picture_id = ankit_bookyourmeal_oct_19_dev.image.picture_id where ankit_bookyourmeal_oct_19_dev.food.food_name like %?%;", nativeQuery = true)
List<Object[]> searchFoods(String searchKey);
}
