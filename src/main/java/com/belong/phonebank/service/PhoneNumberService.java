package com.belong.phonebank.service;

import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;

public interface PhoneNumberService {

    public PhoneNumberResponseDto getAllPhoneNumbers(int pageNo, int pageSize);
    public PhoneNumber getPhoneNumberById(Long id) throws ResourceNotFoundException;
    public PhoneNumber updateActivation(Long phoneNumber, boolean active) throws ResourceNotFoundException;
}
