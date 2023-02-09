package com.belong.phonebank.service.impl;

import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.model.Customer;
import com.belong.phonebank.repository.CustomerRepository;
import com.belong.phonebank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Override
    public Optional<Customer> getCustomer(Long id) throws ResourceNotFoundException {
        Optional<Customer> customer =  repository.findById(id);

        // Check if customer is present
        if(!customer.isPresent())
            throw  new ResourceNotFoundException("Customer id# " + id + " Not Found " );
        else
            return customer;
    }

}