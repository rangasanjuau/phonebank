package com.belong.phonebank.controller;


import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.service.PhoneNumberService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact/phone-numbers")
@Validated
public class PhoneNumberController {
    @Autowired
    public PhoneNumberService service;


    // Get all phone numbers
    @GetMapping()
    public PhoneNumberResponseDto getPhoneNumbers(@RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number must be greater than or equal to 0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be greater than or equal to 1")
                                                  @Max(value = 100, message = "Page size must be less than or equal to 100") Integer pageSize) {
        return service.getAllPhoneNumbers(pageNo, pageSize);
    }

    // Get a phone number by id
    @GetMapping("/{id}")
    public PhoneNumber getPhoneNumberById(@PathVariable @Positive Long id) throws ResourceNotFoundException {
        return service.getPhoneNumberById(id);
    }

    // activate a phone number bu id
    @PutMapping("/activate/{id}")
    public PhoneNumber activateNumber(@PathVariable @Positive Long id) throws ResourceNotFoundException {
        return service.updateActivation(id, true);
    }


}
