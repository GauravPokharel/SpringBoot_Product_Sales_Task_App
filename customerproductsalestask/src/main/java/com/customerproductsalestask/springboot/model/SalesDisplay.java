package com.customerproductsalestask.springboot.model;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


//Used for form
public class SalesDisplay {
	private List<ProductModel> products;
	private List<CustomerModel> customers;
	
	  @Min(value=1, message="Quantity can not be less than one ")
	  @NotNull(message ="Quantity field can not be empty")
	private int quantities;
	  @NotNull(message ="Customer field can not be empty")
	private int customerId;
	  @NotNull(message ="Product field can not be empty")
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
