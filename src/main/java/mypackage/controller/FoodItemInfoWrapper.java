package mypackage.controller;

import java.io.Serializable;

public class FoodItemInfoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public String foodName;
	public String categoryName;
	public String description;
	public Double price;
	public Integer foodId;
	
	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		 this.foodName = foodName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		 this.categoryName = categoryName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		 this.description = description;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		 this.price = price;
	}
	
	public Integer getFoodId() {
		return foodId;
	}
	
	public void setFoodId(Integer foodId) {
		 this.foodId = foodId;
	}
	
}
