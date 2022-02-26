package com.mindtree.bookyourmeal.modules.user.dto;

public class FoodsInCartDto {
	int foodId;
	String foodName;
	int foodPrice;
	int amount;
	String foodDescription;
	String imageId;
	String restaurantName;

	public FoodsInCartDto() {
		super();
	}

	
	public FoodsInCartDto(int foodId, String foodName, String restaurantName) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.restaurantName = restaurantName;
	}

	

	public FoodsInCartDto(int foodId, String foodName, int foodPrice, int amount, String foodDescription,
			String imageId, String restaurantName) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.amount = amount;
		this.foodDescription = foodDescription;
		this.imageId = imageId;
		this.restaurantName = restaurantName;
	}
	
	

	public FoodsInCartDto(int foodId, String foodName, int foodPrice, String imageId, String restaurantName) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.imageId = imageId;
		this.restaurantName = restaurantName;
	}


	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getFoodDescription() {
		return foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	@Override
	public String toString() {
		return "FoodsInCartDto [foodId=" + foodId + ", foodName=" + foodName + ", foodPrice=" + foodPrice + ", amount="
				+ amount + ", foodDescription=" + foodDescription + ", imageId=" + imageId + ", restaurantName="
				+ restaurantName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((foodDescription == null) ? 0 : foodDescription.hashCode());
		result = prime * result + foodId;
		result = prime * result + ((foodName == null) ? 0 : foodName.hashCode());
		result = prime * result + foodPrice;
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		result = prime * result + ((restaurantName == null) ? 0 : restaurantName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodsInCartDto other = (FoodsInCartDto) obj;
		if (amount != other.amount)
			return false;
		if (foodDescription == null) {
			if (other.foodDescription != null)
				return false;
		} else if (!foodDescription.equals(other.foodDescription))
			return false;
		if (foodId != other.foodId)
			return false;
		if (foodName == null) {
			if (other.foodName != null)
				return false;
		} else if (!foodName.equals(other.foodName))
			return false;
		if (foodPrice != other.foodPrice)
			return false;
		if (imageId == null) {
			if (other.imageId != null)
				return false;
		} else if (!imageId.equals(other.imageId))
			return false;
		if (restaurantName == null) {
			if (other.restaurantName != null)
				return false;
		} else if (!restaurantName.equals(other.restaurantName))
			return false;
		return true;
	}

}
