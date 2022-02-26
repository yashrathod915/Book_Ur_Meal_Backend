package com.mindtree.bookyourmeal.modules.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.user.entity.Suborder;

@Repository
public interface SuborderRepository extends JpaRepository<Suborder, Integer> {
//	@Query(value ="select\r\n" + 
//			"count(sf.foods_ordered_food_id) as foodcount,\r\n" + 
//			"f.food_name as foodname,\r\n" + 
//			"f.food_price as foodprice,\r\n" + 
//			"c.category_name as category,\r\n" + 
//			"r.restaurant_name as restaurantName,i.data,i.file_name,i.file_type as foodImage\r\n" + 
//			"from suborder_foods_ordered sf\r\n" + 
//			"Inner join food f on sf.foods_ordered_food_id=f.food_id\r\n" + 
//			"Inner join category c on c.category_id=f.food_category_category_id\r\n" + 
//			"Inner join restaurant r on r.restaurant_id=f.restaurant_restaurant_id\r\n" + 
//			"Inner join image i on i.picture_id=f.food_image_picture_id\r\n" + 
//			"group by f.food_id order by  count(sf.foods_ordered_food_id) desc LIMIT 8", nativeQuery=true)
//    List<Object> getMaximumOrderFood();
//	

	@Query(value = "select f.food_id as foodId,f.food_name as foodname,f.food_price as foodprice,\r\n" + 
			"			r.restaurant_name as restaurantName,f.food_image_picture_id from suborder_foods_ordered sf Inner join food f \r\n" + 
			"			on sf.foods_ordered_food_id=f.food_id Inner join restaurant r on \r\n" + 
			"			r.restaurant_id=f.restaurant_restaurant_id group by f.food_id order by \r\n" + 
			"			count(sf.foods_ordered_food_id) desc LIMIT 8", nativeQuery = true)
	List<Object> getMaximumOrderFood();

	@Query(value = "select count(sf.foods_ordered_food_id) as foodcount,f.food_name as foodname,"
			+ "r.restaurant_name as restaurantName from suborder_foods_ordered sf Inner join food f "
			+ "on sf.foods_ordered_food_id=f.food_id Inner join restaurant r on "
			+ "r.restaurant_id=f.restaurant_restaurant_id group by f.food_id order by  "
			+ "count(sf.foods_ordered_food_id) desc LIMIT 10", nativeQuery = true)
	List<Object> getMaximumTenOrderFood();
	
	@Query(value="select count(sf.foods_ordered_food_id) as foodcount,\r\n" + 
			"			r.restaurant_name as restaurantName from suborder_foods_ordered sf Inner join food f \r\n" + 
			"			on sf.foods_ordered_food_id=f.food_id Inner join restaurant r on \r\n" + 
			"			r.restaurant_id=f.restaurant_restaurant_id group by f.restaurant_restaurant_id order by  \r\n" + 
			"			count(sf.foods_ordered_food_id) desc LIMIT 5",nativeQuery = true)
	List<Object> getTopFiveRestaurant();
	
	@Query(value="select count(sf.foods_ordered_food_id) as foodcount,\r\n" + 
			"			r.restaurant_name as restaurantName from suborder_foods_ordered sf Inner join food f \r\n" + 
			"			on sf.foods_ordered_food_id=f.food_id Inner join restaurant r on \r\n" + 
			"			r.restaurant_id=f.restaurant_restaurant_id group by f.restaurant_restaurant_id order by  \r\n" + 
			"			count(sf.foods_ordered_food_id) LIMIT 5",nativeQuery = true)
	List<Object> getLeastFiveRestaurant();

}
