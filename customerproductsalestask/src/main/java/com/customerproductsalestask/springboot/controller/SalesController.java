package com.customerproductsalestask.springboot.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
		salesDisplay.setProducts(productService.getAllProducts());
		salesDisplay.setCustomers(customerService.getAllCustomer());
		model.addAttribute("salesDisplay", salesDisplay);
		return "addSales";
	}
	@PostMapping("/saveSales")
	public String saveSales(@Valid @ModelAttribute("salesDisplay")SalesDisplay salesDisplay, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			salesDisplay.setProducts(productService.getAllProducts());
			salesDisplay.setCustomers(customerService.getAllCustomer());
			return "addSales";
		}
		/*
		 * if(salesDisplay.getQuantities()<=0) {
		 * //System.out.println("Quantities"+salesDisplay.getQuantities());
		 * bindingResult.addError(null); return "addSales"; }
		 */
		productService.updateProductQuantity(salesDisplay.getProductId(), salesDisplay.getQuantities());
		salesService.saveSalesDisplayToSales(salesDisplay,productService.getProductById(salesDisplay.getProductId()));		
		List<SalesModel> salesList= salesService.getAllSales();
		productService.checkProductStockFlag(salesList, salesDisplay.getProductId());
		customerService.checkIsRegularFlag(salesList, salesDisplay.getCustomerId());
		return"redirect:/sales";	
	}
}
