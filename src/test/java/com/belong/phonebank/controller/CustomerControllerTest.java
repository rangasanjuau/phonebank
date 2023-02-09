package com.belong.phonebank.controller;

import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.model.Customer;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService service;


    @Test
    void getCustomerById_validId_returnOKResponse() throws Exception
    {
        Customer customer = new Customer();
        when(service.getCustomer(anyLong())).thenReturn(customer);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/customer/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.customer").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.customer.id").isNotEmpty());;

    }

    @Test
    void getCustomerById_InvalidId_ThrowsBadRequest() throws Exception
    {
        Customer customer = new Customer();
        when(service.getCustomer(anyLong())).thenReturn(customer);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/customer/-2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].message").exists());;
    }

    @Test
    void getCustomerById_nonExistingId_throwsResourceNotFoundException() throws Exception
    {
        Customer customer = new Customer();
        when(service.getCustomer(anyLong())).thenThrow(ResourceNotFoundException.class);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/customer/2000")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").exists());;
    }


    @Test
    void getCustomerPhoneNumbersById_validId_returnOKResponse() throws Exception
    {
        PhoneNumberResponse response = new PhoneNumberResponse(Collections.singletonList(new PhoneNumber()));
        when(service.getCustomerPhonNumbers(anyLong())).thenReturn(response);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/customer/phone-numbers/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumbers").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumbers[*]").isNotEmpty());
    }

    @Test
    void getCustomerPhoneNumbersById_InvalidId_ThrowsBadRequest() throws Exception
    {
        PhoneNumberResponse response = new PhoneNumberResponse(Collections.singletonList(new PhoneNumber()));
        when(service.getCustomerPhonNumbers(anyLong())).thenReturn(response);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/customer/phone-numbers/-3")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].message").exists());;
    }

    @Test
    void getCustomerPhoneNumbersById_nonExistingId_throwsResourceNotFoundException() throws Exception
    {
        Customer customer = new Customer();
        when(service.getCustomerPhonNumbers(anyLong())).thenThrow(ResourceNotFoundException.class);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/customer/phone-numbers/3000")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").exists());;
    }







}