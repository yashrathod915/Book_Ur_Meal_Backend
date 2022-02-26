package com.mindtree.bookyourmeal.modules.user.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.mindtree.bookyourmeal.modules.core.dto.UserDto;

public class FoodOrderDto {
	private int orderId;
	private double orderBillAmount;
	private boolean orderStatus;
	private UserDto userDto;
	private Set<SuborderDto> subordersDto;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public FoodOrderDto() {
		super();
	}

	public FoodOrderDto(int orderId, double orderBillAmount, boolean orderStatus, UserDto userDto,
			Set<SuborderDto> subordersDto, LocalDateTime localDateTime) {
		super();
		this.orderId = orderId;
		this.orderBillAmount = orderBillAmount;
		this.orderStatus = orderStatus;
		this.userDto = userDto;
		this.subordersDto = subordersDto;
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

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Set<SuborderDto> getSubordersDto() {
		return subordersDto;
	}

	public void setSubordersDto(Set<SuborderDto> subordersDto) {
		this.subordersDto = subordersDto;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "FoodOrderDto [orderId=" + orderId + ", orderBillAmount=" + orderBillAmount + ", orderStatus="
				+ orderStatus + ", userDto=" + userDto + ", subordersDto=" + subordersDto + ", localDateTime="
				+ localDateTime + "]";
	}

}