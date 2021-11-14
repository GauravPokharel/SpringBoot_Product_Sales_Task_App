package com.customerproductsalestask.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.customerproductsalestask.springboot.model.ProductModel;
import com.customerproductsalestask.springboot.service.ProductServiceImpl;

@Controller
public class ProductController {
	@Autowired 
	private ProductServiceImpl productService;
	@GetMapping("/product")
	public String indexProduct(Model model) {
		model.addAttribute("listProduct", productService.getAllProducts());
		return "indexProduct";
	}
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		ProductModel product= new ProductModel(); 
		model.addAttribute("product",product); 
		return "addProduct";
	}
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product")ProductModel product) {
		productService.saveProduct(product);
		return "redirect:/product";
	}
	@GetMapping("/updateProduct/{id}")
	public String updateProduct(@PathVariable(value="id") int id, Model model) {
		//get product from the service
		ProductModel product= productService.getProductById(id);
		// set product as a model attribute to pre-populate the update form
		model.addAttribute("product", product);
		return "addProduct";
	}
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value="id") int id) {
		this.productService.deleteProductById(id);
		return "redirect:/product";
	}
	
}
