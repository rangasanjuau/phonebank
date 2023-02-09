package com.belong.phonebank.controller;


import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact/phone-numbers")
public class PhoneNumberController {
    @Autowired
    public PhoneNumberService service;


    // Get all phone numbers
    @GetMapping()
    public PhoneNumberResponseDto getPhoneNumbers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return service.getAllPhoneNumbers(pageNo, pageSize);
    }

    // Get a phone number by id
    @GetMapping("/{id}")
    public PhoneNumber getPhoneNumberById(@PathVariable Long id) throws ResourceNotFoundException {
        return service.getPhoneNumberById(id);
    }

    // activate a phone number bu id
    @PutMapping("/activate/{id}")
    public PhoneNumber activateNumber(@PathVariable Long id) throws ResourceNotFoundException {
        return service.updateActivation(id, true);
    }


}
