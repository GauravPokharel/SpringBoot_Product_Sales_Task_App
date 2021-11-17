package com.customerproductsalestask.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.customerproductsalestask.springboot.model.CustomerModel;
import com.customerproductsalestask.springboot.service.CustomerServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;
	//Display list of Customers
	@GetMapping("/")
	public String indexCustomer(Model model) {
		model.addAttribute("listCustomer", customerService.getAllCustomer());
		return "indexCustomer";
	}
	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		CustomerModel customer = new CustomerModel();
		model.addAttribute("customer", customer);
		return "addCustomer";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer")CustomerModel customer, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addCustomer";
		}
		customerService.saveCustomer(customer);
		return "redirect:/";
		
	}
	@GetMapping("/updateCustomer/{id}")
	public String updateCustomer(@PathVariable(value="id") int id, Model model) {
		CustomerModel customer= customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		return "addCustomer";
	}
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable(value="id") int id) {
		this.customerService.deleteCustomerById(id);
		return "redirect:/";
	}
}
