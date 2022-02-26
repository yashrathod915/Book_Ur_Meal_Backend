package com.mindtree.bookyourmeal.modules.restaurant.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;

public class RestaurantInfoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int restaurantId;
	private String restaurantName;
	private String mailId;
	private String restaurantContactNumber;
	private Address restaurantAddress;
	private float restaurantRating;
	private int restaurantStatus;
	private Set<Image> restaurantImages;
	private LocalDateTime localDateTime = LocalDateTime.now();

	public RestaurantInfoDto(int restaurantId, String restaurantName, Address restaurantAddress, String mailId,
			String restaurantContactNumber, float restaurantRating, int restaurantStatus, Set<Image> restaurantImages,
			LocalDateTime localDateTime) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.mailId = mailId;
		this.restaurantContactNumber = restaurantContactNumber;
		this.restaurantRating = restaurantRating;
		this.restaurantStatus = restaurantStatus;
		this.restaurantImages = restaurantImages;
		this.localDateTime = localDateTime;
	}

	public RestaurantInfoDto() {
		super();
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

	@Override
	public String toString() {
		return "RestaurantInfoDto [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantAddress=" + restaurantAddress + ", mailId=" + mailId + ", restaurantContactNumber="
				+ restaurantContactNumber + ", restaurantRating=" + restaurantRating + ", restaurantStatus="
				+ restaurantStatus + ", restaurantImages=" + restaurantImages + ", localDateTime=" + localDateTime
				+ "]";
	}

}
