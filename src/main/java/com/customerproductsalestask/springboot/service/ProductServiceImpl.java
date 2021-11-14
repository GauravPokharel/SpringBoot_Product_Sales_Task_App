package com.customerproductsalestask.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerproductsalestask.springboot.model.ProductModel;
import com.customerproductsalestask.springboot.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<ProductModel> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public void saveProduct(ProductModel product) {
		this.productRepo.save(product); 
	}

	@Override
	public ProductModel getProductById(int id) {
		Optional<ProductModel> optional= productRepo.findById(id);
		ProductModel product=null;
		if(optional.isPresent()) {
			product=optional.get();
		}else
		{
			throw new RuntimeException("Product not found for id:" +id);
		}
		return product;
	}

	@Override
	public void deleteProductById(int id) {
		this.productRepo.deleteById(id);
	}
	

}
