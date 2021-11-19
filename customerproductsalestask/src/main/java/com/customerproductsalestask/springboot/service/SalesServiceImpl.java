package com.customerproductsalestask.springboot.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerproductsalestask.springboot.model.CustomerModel;
import com.customerproductsalestask.springboot.model.ProductModel;
import com.customerproductsalestask.springboot.model.SalesDisplay;
import com.customerproductsalestask.springboot.model.SalesModel;
import com.customerproductsalestask.springboot.repository.SalesRepository;

@Service
public class SalesServiceImpl implements SalesService {
	@Autowired
	private SalesRepository salesRepository;
	@Override
	public void saveSales(SalesModel sales) {
		salesRepository.save(sales);		
	}
	@Override
	public List<SalesModel> getAllSales() {
		return salesRepository.findAll();
	}
	@Override
	public void saveSalesDisplayToSales(int quantity, ProductModel product, CustomerModel customer) {
		SalesModel sales=new SalesModel();
		sales.setCustomer(customer);
		sales.setProduct(product);
		sales.setQuantity(quantity);
		
		double price=quantity*product.getProductPrice();
		sales.setPrice(price);
		sales.setDateOfSales(LocalDate.now());
		saveSales(sales);		
	}
}
