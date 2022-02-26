package com.mindtree.bookyourmeal.modules.user.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.mindtree.bookyourmeal.modules.restaurant.dto.FoodDto;

public class SuborderDto {

	private int suborderId;
	private double suborderBillAmount;
	private boolean suborderStatus;
	private FoodOrderDto foodOrderDto;
	private Set<FoodDto> foodsOrderedDto;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public SuborderDto() {
		super();
	}

	public SuborderDto(int suborderId, double suborderBillAmount, boolean suborderStatus, FoodOrderDto foodOrderDto,
			Set<FoodDto> foodsOrderedDto, LocalDateTime localDateTime) {
		super();
		this.suborderId = suborderId;
		this.suborderBillAmount = suborderBillAmount;
		this.suborderStatus = suborderStatus;
		this.foodOrderDto = foodOrderDto;
		this.foodsOrderedDto = foodsOrderedDto;
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

	public boolean isSuborderstatus() {
		return suborderStatus;
	}

	public void setSuborderstatus(boolean suborderstatus) {
		this.suborderStatus = suborderstatus;
	}

	public FoodOrderDto getFoodOrderDto() {
		return foodOrderDto;
	}

	public void setFoodOrderDto(FoodOrderDto foodOrderDto) {
		this.foodOrderDto = foodOrderDto;
	}

	public Set<FoodDto> getFoodsOrderedDto() {
		return foodsOrderedDto;
	}

	public void setFoodsOrderedDto(Set<FoodDto> foodsOrderedDto) {
		this.foodsOrderedDto = foodsOrderedDto;
	}

	public boolean isSuborderStatus() {
		return suborderStatus;
	}

	public void setSuborderStatus(boolean suborderStatus) {
		this.suborderStatus = suborderStatus;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "SuborderDto [suborderId=" + suborderId + ", suborderBillAmount=" + suborderBillAmount
				+ ", suborderStatus=" + suborderStatus + ", foodOrderDto=" + foodOrderDto + ", foodsOrderedDto="
				+ foodsOrderedDto + ", localDateTime=" + localDateTime + "]";
	}

}
