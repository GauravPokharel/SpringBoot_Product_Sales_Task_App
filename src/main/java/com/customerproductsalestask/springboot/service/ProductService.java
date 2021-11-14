package com.customerproductsalestask.springboot.service;

import java.util.List;

import com.customerproductsalestask.springboot.model.ProductModel;

public interface ProductService {
	List<ProductModel> getAllProducts();
	void saveProduct(ProductModel product);
	ProductModel getProductById(int id);
	void deleteProductById(int id);
}
