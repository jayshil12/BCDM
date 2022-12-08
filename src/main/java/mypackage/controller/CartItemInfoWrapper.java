package mypackage.controller;

import java.io.Serializable;

public class CartItemInfoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public String foodName;
	public Integer quantity;
	public Integer foodId;
	public Double price;
	public Double total;
	public Double tax;
	public Integer userId;
	
	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		 this.foodName = foodName;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		 this.quantity = quantity;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		 this.price = price;
	}
	
	public Double getTax() {
		return tax;
	}
	
	public void setTax(Double tax) {
		 this.tax = tax;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		 this.total = total;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		 this.userId = userId;
	}
	
	public Integer getFoodId() {
		return foodId;
	}
	
	public void setFoodId(Integer foodId) {
		 this.foodId = foodId;
	}
}
