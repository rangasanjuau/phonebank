package com.belong.phonebank.controller;


import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.dto.CustomerResponse;
import com.belong.phonebank.dto.CustomerResponseDto;
import com.belong.phonebank.service.CustomerService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    @Autowired
    public CustomerService service;

    @GetMapping(value = "/{id}")
    public CustomerResponseDto getPhoneNumbersById(@PathVariable @Positive Long id) throws ResourceNotFoundException {
        return new CustomerResponseDto(new CustomerResponse(service.getCustomer(id)));
    }
}
