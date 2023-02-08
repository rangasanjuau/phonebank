package com.belong.phonebank.service.impl;

import com.belong.phonebank.Exception.ResourceNotFoundException;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.repository.PhoneNumberRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        List<PhoneNumber> list = getPhoneNumberList(listSize);

        when(repository.findAll()).thenReturn(list);

        List<PhoneNumber> phoneNumbers = phoneNumberService.getAllPhoneNumbers();

        assertEquals(listSize, phoneNumbers.size());
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


    public List<PhoneNumber> getPhoneNumberList(int listSize) {
        List<PhoneNumber> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setPhoneNumberId(i);
            phoneNumber.setActive(false);
            list.add(phoneNumber);
        }
        return list;
    }
}