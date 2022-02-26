package com.mindtree.bookyourmeal.modules.core.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestaurantWithoutFoodsDto {
	
	private int restaurantId;
	private String restaurantName;
	private Address restaurantAddress;
	private String mailId;
	private long restaurantContactNumber;
	private float restaurantRating;
	private boolean restaurantStatus;
	@JsonIgnore
	private Set<Food> foods;
	private Set<Image> restaurantImages;
	private LocalDateTime localDateTime=LocalDateTime.now();
	public RestaurantWithoutFoodsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestaurantWithoutFoodsDto(int restaurantId, String restaurantName, Address restaurantAddress, String mailId,
			long restaurantContactNumber, float restaurantRating, boolean restaurantStatus, Set<Food> foods,
			Set<Image> restaurantImages, LocalDateTime localDateTime) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.mailId = mailId;
		this.restaurantContactNumber = restaurantContactNumber;
		this.restaurantRating = restaurantRating;
		this.restaurantStatus = restaurantStatus;
		this.foods = foods;
		this.restaurantImages = restaurantImages;
		this.localDateTime = localDateTime;
	}
	@Override
	public String toString() {
		return "RestaurantWithoutFoodsDto [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantAddress=" + restaurantAddress + ", mailId=" + mailId + ", restaurantContactNumber="
				+ restaurantContactNumber + ", restaurantRating=" + restaurantRating + ", restaurantStatus="
				+ restaurantStatus + ", foods=" + foods + ", restaurantImages=" + restaurantImages + ", localDateTime="
				+ localDateTime + "]";
	}
	
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public Address getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(Address restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public long getRestaurantContactNumber() {
		return restaurantContactNumber;
	}
	public void setRestaurantContactNumber(long restaurantContactNumber) {
		this.restaurantContactNumber = restaurantContactNumber;
	}
	public float getRestaurantRating() {
		return restaurantRating;
	}
	public void setRestaurantRating(float restaurantRating) {
		this.restaurantRating = restaurantRating;
	}
	public boolean isRestaurantStatus() {
		return restaurantStatus;
	}
	public void setRestaurantStatus(boolean restaurantStatus) {
		this.restaurantStatus = restaurantStatus;
	}
	public Set<Food> getFoods() {
		return foods;
	}
	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}
	public Set<Image> getRestaurantImages() {
		return restaurantImages;
	}
	public void setRestaurantImages(Set<Image> restaurantImages) {
		this.restaurantImages = restaurantImages;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	


}
