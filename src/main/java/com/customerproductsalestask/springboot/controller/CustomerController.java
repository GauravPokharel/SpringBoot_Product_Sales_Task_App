package com.customerproductsalestask.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.customerproductsalestask.springboot.model.CustomerModel;
import com.customerproductsalestask.springboot.service.CustomerService;
import com.customerproductsalestask.springboot.service.CustomerServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;
	//Display list of Customers
	@GetMapping("/")
	public String indexCustomer(Model model) {
		model.addAttribute("listCustomer", customerService.getAllCustomer());
		
		/*
		 * for(CustomerModel customer: customerService.getAllCustomer()) { for(String
		 * customerAddress: customer.getAddresses()) {
		 * System.out.println("add="+customerAddress);} }
		 */
		return "indexCustomer";
	}
	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		CustomerModel customer = new CustomerModel();
		model.addAttribute("customer", customer);
		return "addCustomer";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")CustomerModel customer) {
		customerService.saveCustomer(customer);
		return "redirect:/";
	}
	@GetMapping("/updateCustomer/{id}")
	public String updateCustomer(@PathVariable(value="id") int id, Model model) {
		//get customer from the service
		CustomerModel customer= customerService.getCustomerById(id);
		// set customer as a model attribute to pre-populate the update form
		model.addAttribute("customer", customer);
		//System.out.println("get"+customer.getDateOfBirth());
		return "updateCustomer";
	}
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable(value="id") int id) {
		this.customerService.deleteCustomerById(id);
		return "redirect:/";
	}
}
