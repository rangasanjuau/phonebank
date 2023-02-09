package com.belong.phonebank.service;

import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;

import java.util.List;

public interface PhoneNumberService {

    public PhoneNumberResponseDto getAllPhoneNumbers(int pageNo, int pageSize);
    public PhoneNumber getPhoneNumberById(Long id) throws ResourceNotFoundException;
    public PhoneNumber updateActivation(Long phoneNumber, boolean active) throws ResourceNotFoundException;
}
