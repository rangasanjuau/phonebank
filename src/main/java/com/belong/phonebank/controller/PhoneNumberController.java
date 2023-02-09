package com.belong.phonebank.controller;


import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact/phone")
public class PhoneNumberController {
    @Autowired
    public PhoneNumberService service;

    @GetMapping("/all")
    public PhoneNumberResponseDto getPhoneNumbers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return service.getAllPhoneNumbers(pageNo, pageSize);
    }

    @PutMapping("/activate/{id}")
    public PhoneNumber activateNumber(@PathVariable Long id) throws ResourceNotFoundException {
        return service.updateActivation(id, true);
    }


}
