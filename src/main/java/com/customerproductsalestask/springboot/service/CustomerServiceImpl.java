package com.customerproductsalestask.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerproductsalestask.springboot.model.CustomerModel;
import com.customerproductsalestask.springboot.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public List<CustomerModel> getAllCustomer() {
		
		return customerRepository.findAll();
	}
	@Override
	public void saveCustomer(CustomerModel customer) {
		this.customerRepository.save(customer);		
	}
	
	@Override
	public CustomerModel getCustomerById(int id) {
		Optional<CustomerModel> optional= customerRepository.findById(id);
		CustomerModel customer=null;
		if(optional.isPresent()) {
			customer=optional.get();
		}else {
			throw new RuntimeException("Customer not found for id:" +id);
		}
		return customer;
	}
	@Override
	public void deleteCustomerById(int id) {
		this.customerRepository.deleteById(id);
		
	}

}
