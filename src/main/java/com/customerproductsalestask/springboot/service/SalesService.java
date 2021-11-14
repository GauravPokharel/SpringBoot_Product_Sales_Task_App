package com.customerproductsalestask.springboot.service;

import java.util.List;

import com.customerproductsalestask.springboot.model.SalesModel;

public interface SalesService {
	void saveSales(SalesModel sales);
	List<SalesModel> getAllSales();
}
