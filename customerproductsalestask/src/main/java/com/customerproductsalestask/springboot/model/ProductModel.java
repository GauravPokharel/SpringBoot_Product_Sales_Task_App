package com.customerproductsalestask.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product")
public class ProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty(message="Product name field can not be empty")
	@Column(name="product_name", nullable=false, length = 100)
	private String productName;
	@NotNull(message="Product price field can not be empty")
	@Min(value=1, message="Product price can not be less than 1")
	@Column(name="product_price", nullable=false)
	private double productPrice;
	@Column(name="product_company_name", nullable=true)
	private String productCompanyName;	
	@Column(name="product_stock")
	private boolean productStock;
	@Min(value=0, message="Product quantity can not be less than 0")
	@NotNull(message="Product quantity field can not be empty")
	@Column(name="product_quantity")
	private int productQuantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCompanyName() {
		return productCompanyName;
	}
	public void setProductCompanyName(String productCompanyName) {
		this.productCompanyName = productCompanyName;
	}
	public boolean isProductStock() {
		return productStock;
	}
	public void setProductStock(boolean productStock) {
		this.productStock = productStock;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}	
}
