package com.belong.phonebank.service.impl;

import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.repository.PhoneNumberRepository;
import com.belong.phonebank.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    private PhoneNumberRepository repository;

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {
        return repository.findAll();
    }

    @Override
    public PhoneNumber updateActivation(Long id, boolean active) {

        Optional<PhoneNumber> phoneNumber = repository.findById(id);
        PhoneNumber updatedPhoneNumber = phoneNumber.get();
        updatedPhoneNumber.setActive(active);
        return repository.save(updatedPhoneNumber);
    }
}