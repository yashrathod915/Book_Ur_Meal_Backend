package com.mindtree.bookyourmeal.modules.core.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Role;
import com.mindtree.bookyourmeal.modules.user.entity.Cart;
import com.mindtree.bookyourmeal.modules.user.entity.FoodOrder;

public class UserDto {
	private int userId;
	private String userName;
	private String mobileNumber;
	private String mailId;
	private String userPassword;
	private Address address;
	private Role role;
	private Set<FoodOrder> pastOrders;
	private Cart cart;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public UserDto() {
		super();
	}

	public UserDto(int userId, String userName, String mobileNumber, String mailId, String userPassword, Address address,
			Role role, Set<FoodOrder> pastOrders, Cart cart, LocalDateTime localDateTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.mailId = mailId;
		this.userPassword = userPassword;
		this.address = address;
		this.role = role;
		this.pastOrders = pastOrders;
		this.cart = cart;
		this.localDateTime = localDateTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<FoodOrder> getPastOrders() {
		return pastOrders;
	}

	public void setPastOrders(Set<FoodOrder> pastOrders) {
		this.pastOrders = pastOrders;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", mobileNumber=" + mobileNumber + ", mailId="
				+ mailId + ", userPassword=" + userPassword + ", address=" + address + ", role=" + role
				+ ", pastOrders=" + pastOrders + ", cart=" + cart + ", localDateTime=" + localDateTime + "]";
	}

}