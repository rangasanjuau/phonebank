package com.belong.phonebank.service;

import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.model.Customer;

import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> getCustomer(Long id) throws ResourceNotFoundException;
}
