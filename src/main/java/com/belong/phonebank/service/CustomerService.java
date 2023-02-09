package com.belong.phonebank.service;

import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.model.Customer;

public interface CustomerService {
    public Customer getCustomer(Long id) throws ResourceNotFoundException;
}
