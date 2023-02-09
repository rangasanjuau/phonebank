package com.belong.phonebank.service.impl;

import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.dto.Meta;
import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.repository.PhoneNumberRepository;
import com.belong.phonebank.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    private PhoneNumberRepository repository;

    @Override
    public PhoneNumberResponseDto getAllPhoneNumbers(int pageNo, int pageSize) {
        // Prepare page information
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<PhoneNumber> pagedResult = repository.findAll(paging);

        // Check if result has contents
        if (pagedResult.hasContent()) {
            return new PhoneNumberResponseDto(new PhoneNumberResponse(pagedResult.getContent()),
                    new Meta(pagedResult.getTotalElements(), pagedResult.getTotalPages()));
        } else {
            // else return empty reponse
            return new PhoneNumberResponseDto(new PhoneNumberResponse(new ArrayList<PhoneNumber>()),
                    new Meta());
        }

    }

    @Override
    public PhoneNumber getPhoneNumberById(Long id) throws ResourceNotFoundException {

        Optional<PhoneNumber> phoneNumber = repository.findById(id);
        if (!phoneNumber.isPresent())
            throw new ResourceNotFoundException("Phone number with id  " + id + " Not Found ");

        return phoneNumber.get();
    }

    @Override
    public PhoneNumber updateActivation(Long id, boolean active) throws ResourceNotFoundException {

        Optional<PhoneNumber> phoneNumber = repository.findById(id);

        // Check if phoneNumber is present
        if (!phoneNumber.isPresent())
            throw new ResourceNotFoundException("Phone number with id  " + id + " Not Found ");

        PhoneNumber updatedPhoneNumber = phoneNumber.get();
        updatedPhoneNumber.setActive(active);
        return repository.save(updatedPhoneNumber);
    }
}