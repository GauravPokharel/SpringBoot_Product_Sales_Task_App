package com.customerproductsalestask.springboot.model;

import java.time.LocalDate;

public class SalesDisplayTable implements Comparable<SalesDisplayTable> {
	private String customerName;
	private String productName;
	private int quantity;
	private double price;
	private LocalDate dateOfSale;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getDateOfSale() {
		return dateOfSale;
	}
	public void setDateOfSale(LocalDate dateOfSale) {
		this.dateOfSale = dateOfSale;
	}
	@Override
	public int compareTo(SalesDisplayTable o) {
		return o.getDateOfSale().compareTo(getDateOfSale());
	}
	
}
