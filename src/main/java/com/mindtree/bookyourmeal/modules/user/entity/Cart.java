package com.mindtree.bookyourmeal.modules.user.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	@ManyToMany
	private Set<FoodQuantity> foodsInCart;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public Cart() {
		super();
	}

	public Cart(int cartId, Set<FoodQuantity> foodsInCart, LocalDateTime localDateTime) {
		super();
		this.cartId = cartId;
		this.foodsInCart = foodsInCart;
		this.localDateTime = LocalDateTime.now();
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Set<FoodQuantity> getFoodsInCart() {
		return foodsInCart;
	}

	public void setFoodsInCart(Set<FoodQuantity> foodsInCart) {
		this.foodsInCart = foodsInCart;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", foodsInCart=" + foodsInCart + ", localDateTime=" + localDateTime + "]";
	}

}
