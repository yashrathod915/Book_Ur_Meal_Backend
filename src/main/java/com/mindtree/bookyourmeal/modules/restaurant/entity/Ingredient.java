package com.mindtree.bookyourmeal.modules.restaurant.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ingredientId;
	private String ingredientName;
	private int ingredientQuantity;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	private Nutrient nutrient;
	private LocalDateTime localDateTime=LocalDateTime.now();

	public Ingredient() {
		super();
	}

	public Ingredient(int ingredientId, String ingredientName, int ingredientQuantity, Nutrient nutrient,
			LocalDateTime localDateTime) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.ingredientQuantity = ingredientQuantity;
		this.nutrient = nutrient;
		this.localDateTime = LocalDateTime.now();
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public int getIngredientQuantity() {
		return ingredientQuantity;
	}

	public void setIngredientQuantity(int ingredientQuantity) {
		this.ingredientQuantity = ingredientQuantity;
	}

	public Nutrient getNutrient() {
		return nutrient;
	}

	public void setNutrient(Nutrient nutrient) {
		this.nutrient = nutrient;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName
				+ ", ingredientQuantity=" + ingredientQuantity + ", nutrient=" + nutrient + ", localDateTime="
				+ localDateTime + "]";
	}

}
