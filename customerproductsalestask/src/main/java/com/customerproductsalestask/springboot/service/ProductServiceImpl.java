package com.customerproductsalestask.springboot.service;

import java.util.List;
import java.util.Optional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customerproductsalestask.springboot.exception.BusinessLogicException;
import com.customerproductsalestask.springboot.model.ProductModel;
import com.customerproductsalestask.springboot.model.SalesModel;
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
		try {
			this.productRepo.deleteById(id);}
		catch (ConstraintViolationException e) {
			throw new BusinessLogicException("601", "Can not delete product becasue it is used in sales");
		}
		catch(Exception e) {
			throw new BusinessLogicException("601", "Can not delete product becasue it is used in sales");
		}
		
	}

	@Override
	public void checkProductStockFlag(List<SalesModel> salesList, int productId) {
		int countProduct = 0;
		for (SalesModel item : salesList) {
		   if (item.getProductId()==productId)
			   countProduct++;
		}
		if(countProduct>=20) {
			ProductModel product= getProductById(productId);
			product.setProductStock(true);
			saveProduct(product);
		}
		
	}

	@Override
	public void updateProductQuantity(int productId, int quantity) {
		//System.out.println("this is product quantity update");
		try {
		ProductModel product= getProductById(productId);
		/*
		 * int quantityOfProducttest= product.getProductQuantity()-quantity;
		 * System.out.println(quantityOfProducttest);
		 */
		if(product.getProductQuantity()-quantity<0) {
			//System.out.println("this is if product quantity update");
			throw new BusinessLogicException();
		}else 
		{
			int quantityOfProduct= product.getProductQuantity()-quantity;
			//System.out.println("this is else");
			product.setProductQuantity(quantityOfProduct);
			saveProduct(product);}
		}
		catch(BusinessLogicException e) {
			throw new BusinessLogicException("602", "product qunatity is not enough to make this sales");
		}
		
		
	}
	

}
