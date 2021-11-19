package com.customerproductsalestask.springboot.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="sales")
public class SalesModel {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@OneToOne(cascade= CascadeType.ALL)
@JoinColumn(name="fk_product_id")
private ProductModel product;

@OneToOne(cascade= CascadeType.ALL)
@JoinColumn(name="fk_customer_id")
private CustomerModel customer;
/*
 * @Column(name="product_id", nullable=false) private int productId;
 * 
 * @Column(name="customer_id", nullable=false) private int customerId;
 */
@Column(name="quantity", nullable=false)
private int quantity;
@Column(name="price", nullable=false)
private double price;
@DateTimeFormat(pattern = "yyyy-MM-dd")
@Column(name="date_of_sale")
private LocalDate dateOfSales;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
/*
 * public int getProductId() { return productId; } public void setProductId(int
 * productId) { this.productId = productId; } public int getCustomerId() {
 * return customerId; } public void setCustomerId(int customerId) {
 */
/*
 * this.customerId = customerId; }
 */
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
public LocalDate getDateOfSales() {
	return dateOfSales;
}
public void setDateOfSales(LocalDate dateOfSales) {
	this.dateOfSales = dateOfSales;
}
public ProductModel getProduct() {
	return product;
}
public void setProduct(ProductModel product) {
	this.product = product;
}
public CustomerModel getCustomer() {
	return customer;
}
public void setCustomer(CustomerModel customer) {
	this.customer = customer;
}

}
