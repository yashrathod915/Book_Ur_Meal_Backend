package com.mindtree.bookyourmeal.modules.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;

@Entity
public class FoodQuantity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodQuantityId;
	@OneToOne
	private Food food;
	private int foodQuantity;

	public FoodQuantity() {
		super();
	}

	public FoodQuantity(int foodQuantityId, Food food, int foodQuantity) {
		super();
		this.foodQuantityId = foodQuantityId;
		this.food = food;
		this.foodQuantity = foodQuantity;
	}

	public int getFoodQuantityId() {
		return foodQuantityId;
	}

	public void setFoodQuantityId(int foodQuantityId) {
		this.foodQuantityId = foodQuantityId;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	@Override
	public String toString() {
		return "FoodQuantity [foodQuantityId=" + foodQuantityId + ", food=" + food + ", foodQuantity=" + foodQuantity
				+ "]";
	}

}
