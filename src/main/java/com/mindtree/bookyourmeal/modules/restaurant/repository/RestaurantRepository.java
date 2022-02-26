package com.mindtree.bookyourmeal.modules.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;

@EnableJpaRepositories
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	@Query(value="Select distinct a.city from address a INNER JOIN restaurant r \r\n" + 
			"on r.restaurant_address_address_id=a.address_id;",nativeQuery=true)
     List<Object> getAllLocationOfRestaurant();
	
	
	@Query(value="select r.restaurant_id,r.mail_id,r.restaurant_contact_number,\r\n" + 
			"        r.restaurant_name,a.city,a.locality\r\n" + 
			"       from restaurant r inner join address a \r\n" + 
			"       on r.restaurant_address_address_id = a.address_id where a.city=?1",
			nativeQuery=true)
	List<Object> getRestaurantByCity(String city);
	
	@Query(value="Select count(restaurant_id) from restaurant",nativeQuery=true)
	int getAllRestaurant();
	
	@Query(value="Select count(restaurant_id) from restaurant where restaurant_status=0",nativeQuery=true)
    int getPendingApprovalRestaurant();
	
	@Query(value="Select count(restaurant_id) from restaurant where restaurant_status=1",nativeQuery=true)
	int getApprovedRestaurant();
	
	@Query(value="Select count(restaurant_id) from restaurant where restaurant_status=-1",nativeQuery=true)
	int getRejectedRestaurant();
    
	@Query(value="select count(suborder_id) from suborder",nativeQuery=true)
	int getFoodOrders();
    
	Optional<Restaurant> findByRestaurantContactNumber(String restaurantContactNumber);

	
	Optional<Restaurant> findByMailId(String mailId);
	

}