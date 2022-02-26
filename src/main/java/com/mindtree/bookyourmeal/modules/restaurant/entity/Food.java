package com.mindtree.bookyourmeal.modules.restaurant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.bookyourmeal.modules.core.entity.Image;

@AnalyzerDef(name = "textanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
				@Parameter(name = "language", value = "English") }) })
@Entity
@Indexed
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Food implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodId;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(definition = "textanalyzer")
	private String foodName;
	private String foodDescription;
	private int foodPrice;
	private int foodQuantity;
	private float foodRating;
	@OneToOne
	private Nutrient nutrient;
	private int nutrientScore;
	@ManyToOne
	@JsonIgnore
	private Category foodCategory;
	@ManyToOne
	@JsonIgnore
	private Restaurant restaurant;
	@OneToOne(cascade = CascadeType.ALL)
	private Image foodImage;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(definition = "textanalyzer")
	private boolean foodAvailabilityStatus;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Ingredient> ingredients;
	private LocalDateTime localDateTime = LocalDateTime.now();

	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Food(int foodId, String foodName, String foodDescription, int foodPrice, int foodQuantity, float foodRating,
			Nutrient nutrient, int nutrientScore, Category foodCategory, Restaurant restaurant, Image foodImage,
			boolean foodAvailabilityStatus, Set<Ingredient> ingredients, LocalDateTime localDateTime) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodDescription = foodDescription;
		this.foodPrice = foodPrice;
		this.foodQuantity = foodQuantity;
		this.foodRating = foodRating;
		this.nutrient = nutrient;
		this.nutrientScore = nutrientScore;
		this.foodCategory = foodCategory;
		this.restaurant = restaurant;
		this.foodImage = foodImage;
		this.foodAvailabilityStatus = foodAvailabilityStatus;
		this.ingredients = ingredients;
		this.localDateTime = localDateTime;
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

	public String getFoodDescription() {
		return foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	public float getFoodRating() {
		return foodRating;
	}

	public void setFoodRating(float foodRating) {
		this.foodRating = foodRating;
	}

	public Nutrient getNutrient() {
		return nutrient;
	}

	public void setNutrient(Nutrient nutrient) {
		this.nutrient = nutrient;
	}

	public int getNutrientScore() {
		return nutrientScore;
	}

	public void setNutrientScore(int nutrientScore) {
		this.nutrientScore = nutrientScore;
	}

	public Category getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(Category foodCategory) {
		this.foodCategory = foodCategory;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Image getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(Image foodImage) {
		this.foodImage = foodImage;
	}

	public boolean isFoodAvailabilityStatus() {
		return foodAvailabilityStatus;
	}

	public void setFoodAvailabilityStatus(boolean foodAvailabilityStatus) {
		this.foodAvailabilityStatus = foodAvailabilityStatus;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", foodName=" + foodName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (foodAvailabilityStatus ? 1231 : 1237);
		result = prime * result + ((foodCategory == null) ? 0 : foodCategory.hashCode());
		result = prime * result + ((foodDescription == null) ? 0 : foodDescription.hashCode());
		result = prime * result + foodId;
		result = prime * result + ((foodImage == null) ? 0 : foodImage.hashCode());
		result = prime * result + ((foodName == null) ? 0 : foodName.hashCode());
		result = prime * result + foodPrice;
		result = prime * result + foodQuantity;
		result = prime * result + Float.floatToIntBits(foodRating);
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((localDateTime == null) ? 0 : localDateTime.hashCode());
		result = prime * result + ((nutrient == null) ? 0 : nutrient.hashCode());
		result = prime * result + nutrientScore;
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
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
		Food other = (Food) obj;
		if (foodAvailabilityStatus != other.foodAvailabilityStatus)
			return false;
		if (foodCategory == null) {
			if (other.foodCategory != null)
				return false;
		} else if (!foodCategory.equals(other.foodCategory))
			return false;
		if (foodDescription == null) {
			if (other.foodDescription != null)
				return false;
		} else if (!foodDescription.equals(other.foodDescription))
			return false;
		if (foodId != other.foodId)
			return false;
		if (foodImage == null) {
			if (other.foodImage != null)
				return false;
		} else if (!foodImage.equals(other.foodImage))
			return false;
		if (foodName == null) {
			if (other.foodName != null)
				return false;
		} else if (!foodName.equals(other.foodName))
			return false;
		if (foodPrice != other.foodPrice)
			return false;
		if (foodQuantity != other.foodQuantity)
			return false;
		if (Float.floatToIntBits(foodRating) != Float.floatToIntBits(other.foodRating))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (localDateTime == null) {
			if (other.localDateTime != null)
				return false;
		} else if (!localDateTime.equals(other.localDateTime))
			return false;
		if (nutrient == null) {
			if (other.nutrient != null)
				return false;
		} else if (!nutrient.equals(other.nutrient))
			return false;
		if (nutrientScore != other.nutrientScore)
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		return true;
	}

}
