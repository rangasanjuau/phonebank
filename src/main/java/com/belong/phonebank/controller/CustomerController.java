package com.belong.phonebank.controller;


import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.dto.CustomerResponse;
import com.belong.phonebank.dto.CustomerResponseDto;
import com.belong.phonebank.service.CustomerService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phone-bank/customer")
@Validated
@CrossOrigin(origins = "https://editor.swagger.io")
public class CustomerController {

    @Autowired
    public CustomerService service;


    // Get Customer by id
    @GetMapping(value = "/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable @Positive(message ="Invalid Customer Id") Long id) throws ResourceNotFoundException {
        return new CustomerResponseDto(new CustomerResponse(service.getCustomer(id)));
    }


    // Get Customer's all phone numbers by Customer id
    @GetMapping(value = "/phone-numbers/{id}")
    public PhoneNumberResponse getCustomerPhoneNumbersById(@PathVariable @Positive(message ="Invalid Customer Id") Long id) throws ResourceNotFoundException {
        return service.getCustomerPhonNumbers(id);
    }
}
