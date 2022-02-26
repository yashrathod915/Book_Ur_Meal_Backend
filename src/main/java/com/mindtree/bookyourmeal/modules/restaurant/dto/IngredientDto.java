package com.mindtree.bookyourmeal.modules.restaurant.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class IngredientDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ingredientId;
	private String ingredientName;
	private int ingredientQuantity;
	private NutrientDto nutrient;
	private LocalDateTime localDateTime=LocalDateTime.now();

	public IngredientDto() {
		super();
	}

	public IngredientDto(int ingredientId, String ingredientName, int ingredientQuantity, NutrientDto nutrient,
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

	public NutrientDto getNutrient() {
		return nutrient;
	}

	public void setNutrient(NutrientDto nutrient) {
		this.nutrient = nutrient;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "IngredientDto [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName
				+ ", ingredientQuantity=" + ingredientQuantity + ", nutrient=" + nutrient + ", localDateTime="
				+ localDateTime + "]";
	}

}
