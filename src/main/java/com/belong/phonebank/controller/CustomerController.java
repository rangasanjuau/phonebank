package com.belong.phonebank.controller;


import com.belong.phonebank.model.Customer;
import com.belong.phonebank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    public CustomerService service;

    @GetMapping(value = "/{id}")
    public Customer getPhoneNumbersById(@PathVariable Long id) {
        return service.getCustomer(id).get();
    }
}
