package com.mindtree.bookyourmeal.modules.core.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.user.entity.Cart;

@Entity
@JsonIgnoreProperties("suborders")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userPassword;
	private String userName;
	private String mobileNumber;
	private String mailId;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToOne
	private Role role;
	@OneToOne
	private Cart cart;
	private LocalDateTime localDateTime = LocalDateTime.now();

	public User() {
	}

	public User(int userId, String userPassword, String userName, String mobileNumber, String mailId, Address address,
			Role role, LocalDateTime localDateTime) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.mailId = mailId;
		this.address = address;
		this.role = role;
		this.localDateTime = localDateTime;
	}

	public User(int userId, String userPassword, String userName, String mobileNumber, String mailId, Address address,
			Role role, Cart cart, LocalDateTime localDateTime) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.mailId = mailId;
		this.address = address;
		this.role = role;
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
		return "User [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", mobileNumber=" + mobileNumber + ", mailId=" + mailId + ", address=" + address + ", role=" + role
				+ ", cart=" + cart + ", localDateTime=" + localDateTime + "]";
	}

}
