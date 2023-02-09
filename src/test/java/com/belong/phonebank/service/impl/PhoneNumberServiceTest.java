package com.belong.phonebank.service.impl;

import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.dto.Meta;
import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.repository.PhoneNumberRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PhoneNumberServiceTest {

    private static final int listSize = 10;

    @InjectMocks
    PhoneNumberServiceImpl phoneNumberService;
    @Mock
    PhoneNumberRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAllPhoneNumbers_ShouldReturnPhoneNumber() {
        int pageNo = 0;
        int pageSize = 5;
        Page<PhoneNumber> pageResult = getPhoneNumberDto(listSize);
        Pageable paging = PageRequest.of(pageNo, pageSize);
        when(repository.findAll(paging)).thenReturn(pageResult);

        Page<PhoneNumber> pagedResult = repository.findAll(paging);

        PhoneNumberResponseDto responseDto = phoneNumberService.getAllPhoneNumbers(pageNo, pageSize);
        PhoneNumberResponse response = responseDto.getData();
        Meta meta = responseDto.getMeta();

        assertEquals(listSize, response.getPhoneNumbers().size());
        assertEquals(listSize, meta.getTotalRecords());
        assertEquals(2, meta.getTotalPages());

    }

    @Test
    public void updateActivation_ShouldUpdatePhoneNumberAndReturnIt() throws ResourceNotFoundException {
        long id = 1L;
        boolean active = false;
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumberId(id);
        phoneNumber.setActive(!active);
        when(repository.findById(id)).thenReturn(Optional.of(phoneNumber));
        when(repository.save(any(PhoneNumber.class))).thenReturn(phoneNumber);

        PhoneNumber updatedPhoneNumber = phoneNumberService.updateActivation(id, active);

        assertEquals(id, updatedPhoneNumber.getPhoneNumberId());
        assertEquals(active, updatedPhoneNumber.isActive());
    }


    public Page<PhoneNumber> getPhoneNumberDto(int listSize) {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setPhoneNumberId(i);
            phoneNumber.setActive(false);
            phoneNumbers.add(phoneNumber);
        }

        Pageable paging = PageRequest.of(0, 5);
        Page<PhoneNumber> pagedResult = new PageImpl<>(phoneNumbers, paging, phoneNumbers.size());

        return pagedResult;
    }
}