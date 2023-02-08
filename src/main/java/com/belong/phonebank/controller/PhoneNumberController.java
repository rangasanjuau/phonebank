package com.belong.phonebank.controller;


import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact/phone")
public class PhoneNumberController {
    @Autowired
    public PhoneNumberService service;

    @GetMapping("/all")
    public PhoneNumberResponseDto getPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = service.getAllPhoneNumbers();
        return new PhoneNumberResponseDto(new PhoneNumberResponse(phoneNumbers));
    }

    @PutMapping("/activate/{id}")
    public PhoneNumber activateNumber(@PathVariable Long id) throws ResourceNotFoundException {
        return service.updateActivation(id, true);
    }


}
