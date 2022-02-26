package com.mindtree.bookyourmeal.modules.user.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.core.entity.User;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private double orderBillAmount;
	private boolean orderStatus;
	@ManyToOne
	@JsonIgnoreProperties("pastOrders")
	private User user;
	@OneToMany
	private Set<Suborder> suborders;
	private LocalDateTime localDateTime = LocalDateTime.now();

	public FoodOrder() {
		super();
	}

	public FoodOrder(int orderId, double orderBillAmount, boolean orderStatus, User user, Set<Suborder> suborders,
			LocalDateTime localDateTime) {
		super();
		this.orderId = orderId;
		this.orderBillAmount = orderBillAmount;
		this.orderStatus = orderStatus;
		this.user = user;
		this.suborders = suborders;
		this.localDateTime = LocalDateTime.now();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderBillAmount() {
		return orderBillAmount;
	}

	public void setOrderBillAmount(double orderBillAmount) {
		this.orderBillAmount = orderBillAmount;
	}

	public boolean isOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Suborder> getSuborders() {
		return suborders;
	}

	public void setSuborders(Set<Suborder> suborders) {
		this.suborders = suborders;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "FoodOrder [orderId=" + orderId + ", orderBillAmount=" + orderBillAmount + ", orderStatus=" + orderStatus
				+ ", user=" + user + ", suborders=" + suborders + ", localDateTime=" + localDateTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localDateTime == null) ? 0 : localDateTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(orderBillAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + orderId;
		result = prime * result + (orderStatus ? 1231 : 1237);
		result = prime * result + ((suborders == null) ? 0 : suborders.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		FoodOrder other = (FoodOrder) obj;
		if (localDateTime == null) {
			if (other.localDateTime != null)
				return false;
		} else if (!localDateTime.equals(other.localDateTime))
			return false;
		if (Double.doubleToLongBits(orderBillAmount) != Double.doubleToLongBits(other.orderBillAmount))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (suborders == null) {
			if (other.suborders != null)
				return false;
		} else if (!suborders.equals(other.suborders))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
