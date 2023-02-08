package com.belong.phonebank.service;

import com.belong.phonebank.model.PhoneNumber;

import java.util.List;

public interface PhoneNumberService {

    public List<PhoneNumber> getAllPhoneNumbers();
    public PhoneNumber updateActivation(Long phoneNumber, boolean active);
}
