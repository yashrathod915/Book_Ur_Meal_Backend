package com.mindtree.bookyourmeal.modules.restaurant.dto;

import java.time.LocalDateTime;

public class NutrientDto {
	private int nutrientId;
	private int noOfCalories;
	private float protein;
	private int cholestrol;
	private float mufa;
	private float pufa;
	private LocalDateTime localDateTime=LocalDateTime.now();
	public NutrientDto() {
		super();
	}
	public NutrientDto(int nutrientId, int noOfCalories, float protein, int cholestrol, float mufa, float pufa,
			LocalDateTime localDateTime) {
		super();
		this.nutrientId = nutrientId;
		this.noOfCalories = noOfCalories;
		this.protein = protein;
		this.cholestrol = cholestrol;
		this.mufa = mufa;
		this.pufa = pufa;
		this.localDateTime = LocalDateTime.now();
	}
	public int getNutrientId() {
		return nutrientId;
	}
	public void setNutrientId(int nutrientId) {
		this.nutrientId = nutrientId;
	}
	public int getNoOfCalories() {
		return noOfCalories;
	}
	public void setNoOfCalories(int noOfCalories) {
		this.noOfCalories = noOfCalories;
	}
	public float getProtein() {
		return protein;
	}
	public void setProtein(float protein) {
		this.protein = protein;
	}
	public int getCholestrol() {
		return cholestrol;
	}
	public void setCholestrol(int cholestrol) {
		this.cholestrol = cholestrol;
	}
	public float getMufa() {
		return mufa;
	}
	public void setMufa(float mufa) {
		this.mufa = mufa;
	}
	public float getPufa() {
		return pufa;
	}
	public void setPufa(float pufa) {
		this.pufa = pufa;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	@Override
	public String toString() {
		return "NutrientDto [nutrientId=" + nutrientId + ", noOfCalories=" + noOfCalories + ", protein=" + protein
				+ ", cholestrol=" + cholestrol + ", mufa=" + mufa + ", pufa=" + pufa + ", localDateTime="
				+ localDateTime + "]";
	}

	
}
