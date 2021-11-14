package com.customerproductsalestask.springboot.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.customerproductsalestask.springboot.model.CustomerModel;
import com.customerproductsalestask.springboot.model.ProductModel;
import com.customerproductsalestask.springboot.model.SalesDisplay;
import com.customerproductsalestask.springboot.model.SalesDisplayTable;
import com.customerproductsalestask.springboot.model.SalesModel;
import com.customerproductsalestask.springboot.service.CustomerServiceImpl;
import com.customerproductsalestask.springboot.service.ProductServiceImpl;
import com.customerproductsalestask.springboot.service.SalesServiceImpl;

@Controller
public class SalesController {
	@Autowired 
	private ProductServiceImpl productService;
	@Autowired
	private CustomerServiceImpl customerService;
	@Autowired 
	private SalesServiceImpl salesService;
	@GetMapping("/sales")
	public String sales(Model model) {
		List<SalesModel> salesList= salesService.getAllSales();
		List<SalesDisplayTable> salesDispalyTableList=new ArrayList<SalesDisplayTable>();
		for(SalesModel sale:salesList) {
			SalesDisplayTable salesDisplayTable=new SalesDisplayTable();
			salesDisplayTable.setCustomerName(customerService.getCustomerById(sale.getCustomerId()).getFullName());
			salesDisplayTable.setProductName(productService.getProductById(sale.getProductId()).getProductName());
			salesDisplayTable.setDateOfSale(sale.getDateOfSales());
			salesDisplayTable.setPrice(sale.getPrice());
			salesDisplayTable.setQuantity(sale.getQuantity());
			salesDispalyTableList.add(salesDisplayTable);
		}
		Collections.sort(salesDispalyTableList);
		model.addAttribute("salesDispalyTableList", salesDispalyTableList);
		return "indexSales";
	}
	@GetMapping("/addSales")
	public String addSales(Model model) {
		SalesDisplay salesDisplay= new SalesDisplay();
		List<ProductModel> products= productService.getAllProducts();
		salesDisplay.setProducts(products);
		List<CustomerModel> customers= customerService.getAllCustomer();
		salesDisplay.setCustomers(customers);
		for(ProductModel product:products){
			System.out.println("product name"+product.getProductName());
			}
		for(CustomerModel product:customers){
			System.out.println("customer name"+product.getFullName());
			}
		model.addAttribute("salesDisplay", salesDisplay);
		return "addSales";
	}
	@PostMapping("/saveSales")
	public String saveSales(@ModelAttribute("salesDisplay")SalesDisplay salesDisplay) {
		SalesModel sales=new SalesModel();
		sales.setCustomerId(salesDisplay.getCustomerId());
		sales.setProductId(salesDisplay.getProductId());
		sales.setQuantity(salesDisplay.getQuantities());
		ProductModel product= productService.getProductById(salesDisplay.getProductId());
		double price=salesDisplay.getQuantities()*product.getProductPrice();
		sales.setPrice(price);
		sales.setDateOfSales(LocalDate.now());
		salesService.saveSales(sales);
		
		// for product_stock flag
		List<SalesModel> salesList=salesService.getAllSales();
		int countProduct = 0;
		for (SalesModel item : salesList) {
		   if (item.getProductId()==salesDisplay.getProductId())
			   countProduct++;
		}
		if(countProduct>=20) {
			product.setProductStock(true);
			productService.saveProduct(product);
		}
		// for is_regular flag
		int countCustomer = 0;
		for (SalesModel item : salesList) {
		   if (item.getCustomerId()==salesDisplay.getCustomerId())
			   countCustomer++;
		}
		if(countCustomer>=20) {
			CustomerModel customer= customerService.getCustomerById(salesDisplay.getCustomerId());
			customer.setRegular(true);
			customerService.saveCustomer(customer);
		}
		return"redirect:/sales";	
	}
}
