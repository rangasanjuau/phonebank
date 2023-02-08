package com.belong.phonebank.controller;


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
    public List<PhoneNumber> getPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = service.getAllPhoneNumbers();
        return phoneNumbers;
    }

    @PutMapping("/activate/{id}")
    public PhoneNumber activateNumber(@PathVariable Long id) {
        return service.updateActivation(id, true);
    }


}
