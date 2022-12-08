package mypackage.controller;

import java.io.Serializable;

public class OrderInfoWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	public Double subtotal;
	public Double totaltax;
	public Double total;
	public Integer userId;
	
	public Double getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(Double subtotal) {
		 this.subtotal = subtotal;
	}
	
	public Double getTax() {
		return totaltax;
	}
	
	public void setTax(Double totaltax) {
		 this.totaltax = totaltax;
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
}
