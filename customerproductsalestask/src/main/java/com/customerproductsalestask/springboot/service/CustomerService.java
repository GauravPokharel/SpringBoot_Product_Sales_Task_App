package com.customerproductsalestask.springboot.service;

import java.util.List;


import com.customerproductsalestask.springboot.model.CustomerModel;
import com.customerproductsalestask.springboot.model.SalesModel;

public interface CustomerService {
	List<CustomerModel> getAllCustomer();
	void saveCustomer(CustomerModel customer);
	CustomerModel getCustomerById(int id);
	void deleteCustomerById(int id);
	void checkIsRegularFlag(List<SalesModel> salesList, int customerId);
}
