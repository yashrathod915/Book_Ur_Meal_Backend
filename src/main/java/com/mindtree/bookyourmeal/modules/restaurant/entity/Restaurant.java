package com.mindtree.bookyourmeal.modules.restaurant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Image;

@Entity
@Indexed
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int restaurantId;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(definition = "textanalyzer")
	private String restaurantName;
	private String  restaurantPassword;
	@OneToOne(cascade = CascadeType.ALL)
	private Address restaurantAddress;
	private String mailId;
	private String restaurantContactNumber;
	private float restaurantRating;
	private int restaurantStatus;
	@OneToMany(mappedBy = "restaurant")
	@JsonIgnoreProperties("restaurant")
	private Set<Food> foods;
	@OneToMany
	private Set<Image> restaurantImages;
	private LocalDateTime localDateTime=LocalDateTime.now();

	public Restaurant() {
		super();
	}

	

	public Restaurant(int restaurantId, String restaurantName, String restaurantPassword, Address restaurantAddress,
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

	public int getRestaurantStatus() {
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
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantPassword=" + restaurantPassword + ", restaurantAddress=" + restaurantAddress
				+ ", mailId=" + mailId + ", restaurantContactNumber=" + restaurantContactNumber + ", restaurantRating="
				+ restaurantRating + ", restaurantStatus=" + restaurantStatus + ", localDateTime=" + localDateTime
				+ "]";
	}
	
}