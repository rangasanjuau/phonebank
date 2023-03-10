package com.belong.phonebank.service.impl;

import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.model.Customer;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.repository.CustomerRepository;
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
public class CustomerServiceTest {
    private static final int listSize = 10;
    @InjectMocks
    CustomerServiceImpl customerService;
    @Mock
    CustomerRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getCustomer_ShouldReturnCustomer() throws ResourceNotFoundException {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setPhoneNumbers(getPhoneNumberList(listSize));


        when(repository.findById(any())).thenReturn(Optional.of(customer));

        Customer customer1 = customerService.getCustomer(1L);

        assertEquals(listSize, customer1.getPhoneNumbers().size());
    }


    @Test
    public void getCustomerPhonNumbers_ShouldReturnPhoneNumberResponse() throws ResourceNotFoundException {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setPhoneNumbers(getPhoneNumberList(listSize));


        when(repository.findById(any())).thenReturn(Optional.of(customer));

        PhoneNumberResponse phoneNumberResponse = customerService.getCustomerPhonNumbers(1L);

        assertEquals(listSize, phoneNumberResponse.getPhoneNumbers().size());
    }



    public List<PhoneNumber> getPhoneNumberList(int listSize) {
        List<PhoneNumber> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setId(i);
            phoneNumber.setActive(false);
            list.add(phoneNumber);
        }
        return list;
    }

}
