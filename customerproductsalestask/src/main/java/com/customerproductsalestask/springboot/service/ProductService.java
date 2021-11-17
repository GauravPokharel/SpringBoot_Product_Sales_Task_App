package com.customerproductsalestask.springboot.service;

import java.util.List;

import com.customerproductsalestask.springboot.model.ProductModel;
import com.customerproductsalestask.springboot.model.SalesModel;

public interface ProductService {
	List<ProductModel> getAllProducts();
	void saveProduct(ProductModel product);
	ProductModel getProductById(int id);
	void deleteProductById(int id);
	void checkProductStockFlag(List<SalesModel> salesList, int productId);
	void updateProductQuantity(int productId, int quantity);
}
