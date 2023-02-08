package com.belong.phonebank.service;

import com.belong.phonebank.model.Customer;

import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> getCustomer(Long id);
}
