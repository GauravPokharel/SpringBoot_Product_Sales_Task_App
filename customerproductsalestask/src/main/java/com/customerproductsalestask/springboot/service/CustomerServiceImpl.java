package com.customerproductsalestask.springboot.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerproductsalestask.springboot.exception.BusinessLogicException;
import com.customerproductsalestask.springboot.model.CustomerModel;
import com.customerproductsalestask.springboot.model.SalesModel;
import com.customerproductsalestask.springboot.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

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
		Optional<CustomerModel> optional = customerRepository.findById(id);
		CustomerModel customer = null;
		if (optional.isPresent()) {
			customer = optional.get();
		} else {
			throw new RuntimeException("Customer not found for id:" + id);
		}
		return customer;
	}

	@Override
	public void deleteCustomerById(int id) {
		try {
			this.customerRepository.deleteById(id);}
		catch (ConstraintViolationException e) {
			throw new BusinessLogicException("601", "Can not delete customer becasue it is used in sales");
		}
		catch(Exception e) {
			throw new BusinessLogicException("601", "Can not delete customer becasue it is used in sales");
		}
		

	}

	@Override
	public void checkIsRegularFlag(List<SalesModel> salesList, int customerId) {

		int countCustomer = 0;
		for (SalesModel item : salesList) {
			if (item.getCustomerId() == customerId)
				countCustomer++;
		}
		if (countCustomer >= 20) {
			CustomerModel customer = getCustomerById(customerId);
			customer.setRegular(true);
			saveCustomer(customer);
		}

	}

}
