package com.mindtree.bookyourmeal.modules.user.dto;

import java.time.LocalDateTime;
import java.util.Set;
public class CartDto {
	private int cartId;
	private Set<FoodQuantityDto> foodsInCartDto;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public CartDto() {
		super();
	}

	public CartDto(int cartId, Set<FoodQuantityDto> foodsInCartDto, LocalDateTime localDateTime) {
		super();
		this.cartId = cartId;
		this.foodsInCartDto = foodsInCartDto;
		this.localDateTime = LocalDateTime.now();
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Set<FoodQuantityDto> getFoodsInCartDto() {
		return foodsInCartDto;
	}

	public void setFoodsInCartDto(Set<FoodQuantityDto> foodsInCartDto) {
		this.foodsInCartDto = foodsInCartDto;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "CartDto [cartId=" + cartId + ", foodsInCartDto=" + foodsInCartDto + ", localDateTime=" + localDateTime
				+ "]";
	}

}
