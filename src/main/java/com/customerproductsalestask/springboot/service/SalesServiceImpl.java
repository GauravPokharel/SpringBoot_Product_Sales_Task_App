package com.customerproductsalestask.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
