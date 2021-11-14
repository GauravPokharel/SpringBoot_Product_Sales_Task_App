package com.customerproductsalestask.springboot.model;

import java.util.List;

public class SalesDisplay {
	private List<ProductModel> products;
	private List<CustomerModel> customers;
	private int quantities;
	private int customerId;
	private int productId;
	public List<ProductModel> getProducts() {
		return products;
	}
	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}
	public List<CustomerModel> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerModel> customers) {
		this.customers = customers;
	}
	public int getQuantities() {
		return quantities;
	}
	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

}
