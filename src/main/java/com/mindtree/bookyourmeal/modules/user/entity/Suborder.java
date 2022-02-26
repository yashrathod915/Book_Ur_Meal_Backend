package com.mindtree.bookyourmeal.modules.user.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;

@Entity
public class Suborder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int suborderId;
	private double suborderBillAmount;
	private boolean suborderStatus;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Food> foodsOrdered;
	private LocalDateTime localDateTime = LocalDateTime.now();

	public Suborder() {
		super();
	}

	public Suborder(int suborderId, double suborderBillAmount, boolean suborderStatus, Set<Food> foodsOrdered,
			LocalDateTime localDateTime) {
		super();
		this.suborderId = suborderId;
		this.suborderBillAmount = suborderBillAmount;
		this.suborderStatus = suborderStatus;
		this.foodsOrdered = foodsOrdered;
		this.localDateTime = LocalDateTime.now();
	}

	public int getSuborderId() {
		return suborderId;
	}

	public void setSuborderId(int suborderId) {
		this.suborderId = suborderId;
	}

	public double getSuborderBillAmount() {
		return suborderBillAmount;
	}

	public void setSuborderBillAmount(double suborderBillAmount) {
		this.suborderBillAmount = suborderBillAmount;
	}

	public boolean isSuborderStatus() {
		return suborderStatus;
	}

	public void setSuborderStatus(boolean suborderStatus) {
		this.suborderStatus = suborderStatus;
	}

	public Set<Food> getFoodsOrdered() {
		return foodsOrdered;
	}

	public void setFoodsOrdered(Set<Food> foodsOrdered) {
		this.foodsOrdered = foodsOrdered;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "Suborder [suborderId=" + suborderId + ", suborderBillAmount=" + suborderBillAmount + ", suborderStatus="
				+ suborderStatus + ", foodsOrdered=" + foodsOrdered + ", localDateTime=" + localDateTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foodsOrdered == null) ? 0 : foodsOrdered.hashCode());
		result = prime * result + ((localDateTime == null) ? 0 : localDateTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(suborderBillAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + suborderId;
		result = prime * result + (suborderStatus ? 1231 : 1237);
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
		Suborder other = (Suborder) obj;
		if (foodsOrdered == null) {
			if (other.foodsOrdered != null)
				return false;
		} else if (!foodsOrdered.equals(other.foodsOrdered))
			return false;
		if (localDateTime == null) {
			if (other.localDateTime != null)
				return false;
		} else if (!localDateTime.equals(other.localDateTime))
			return false;
		if (Double.doubleToLongBits(suborderBillAmount) != Double.doubleToLongBits(other.suborderBillAmount))
			return false;
		if (suborderId != other.suborderId)
			return false;
		if (suborderStatus != other.suborderStatus)
			return false;
		return true;
	}

}
