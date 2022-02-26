package com.mindtree.bookyourmeal.modules.restaurant.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestaurantDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int restaurantId;
	private String restaurantName;
	private String restaurantPassword;
	private Address restaurantAddress;
	private String mailId;
	private String restaurantContactNumber;
	private float restaurantRating;
	private int restaurantStatus;
	private Set<Food> foods;
	private Set<Image> restaurantImages;
	private LocalDateTime localDateTime=LocalDateTime.now();

	public RestaurantDto() {
		super();
	}

	public RestaurantDto(int restaurantId, String restaurantName, String restaurantPassword, Address restaurantAddress,
			String mailId, String restaurantContactNumber, float restaurantRating, int restaurantStatus,
			Set<Food> foods, Set<Image> restaurantImages, LocalDateTime localDateTime) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantPassword = restaurantPassword;
		this.restaurantAddress = restaurantAddress;
		this.mailId = mailId;
		this.restaurantContactNumber = restaurantContactNumber;
		this.restaurantRating = restaurantRating;
		this.restaurantStatus = restaurantStatus;
		this.foods = foods;
		this.restaurantImages = restaurantImages;
		this.localDateTime = LocalDateTime.now();
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
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

	public String getRestaurantPassword() {
		return restaurantPassword;
	}

	public void setRestaurantPassword(String restaurantPassword) {
		this.restaurantPassword = restaurantPassword;
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

	public String getRestaurantContactNumber() {
		return restaurantContactNumber;
	}

	public void setRestaurantContactNumber(String restaurantContactNumber) {
		this.restaurantContactNumber = restaurantContactNumber;
	}

	public float getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(float restaurantRating) {
		this.restaurantRating = restaurantRating;
	}

	public int isRestaurantStatus() {
		return restaurantStatus;
	}

	public void setRestaurantStatus(int restaurantStatus) {
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

	@Override
	public String toString() {
		return "RestaurantDto [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantPassword=" + restaurantPassword + ", restaurantAddress=" + restaurantAddress
				+ ", mailId=" + mailId + ", restaurantContactNumber=" + restaurantContactNumber + ", restaurantRating="
				+ restaurantRating + ", restaurantStatus=" + restaurantStatus + ", foods=" + foods
				+ ", restaurantImages=" + restaurantImages + ", localDateTime=" + localDateTime + "]";
	}

}
