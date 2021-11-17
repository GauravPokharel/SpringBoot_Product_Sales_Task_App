package com.customerproductsalestask.springboot.service;

import java.util.List;

import com.customerproductsalestask.springboot.model.ProductModel;
import com.customerproductsalestask.springboot.model.SalesDisplay;
import com.customerproductsalestask.springboot.model.SalesModel;

public interface SalesService {
	void saveSales(SalesModel sales);
	List<SalesModel> getAllSales();
	void saveSalesDisplayToSales(SalesDisplay salesDisplay,ProductModel product);
}
