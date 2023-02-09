package com.belong.phonebank.controller;

import com.belong.phonebank.dto.Meta;
import com.belong.phonebank.dto.PhoneNumberResponse;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.service.PhoneNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PhoneNumberController.class)
class PhoneNumberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhoneNumberService service;

    @Test
    void getPhoneNumbers_returnOKResponse() throws Exception
    {
        PhoneNumberResponseDto responseDto =  new PhoneNumberResponseDto(new PhoneNumberResponse(Collections.singletonList(new PhoneNumber())),
                new Meta());
        when(service.getAllPhoneNumbers(anyInt(),anyInt())).thenReturn(responseDto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/phone-numbers")
                        .param("pageNo","0")
                        .param("pageSize","10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phoneNumbers").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phoneNumbers[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.meta.totalRecords").isNotEmpty());

    }

    @Test
    void getPhoneNumbers_MissingPageParams_returnOKResponse() throws Exception
    {
        PhoneNumberResponseDto responseDto =  new PhoneNumberResponseDto(new PhoneNumberResponse(Collections.singletonList(new PhoneNumber())),
                new Meta());
        when(service.getAllPhoneNumbers(anyInt(),anyInt())).thenReturn(responseDto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/phone-numbers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phoneNumbers").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phoneNumbers[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.meta.totalRecords").isNotEmpty());

    }


    @Test
    void getPhoneNumbers_InvalidPageNo_ThrowsBadRequest() throws Exception
    {
        PhoneNumberResponseDto responseDto =  new PhoneNumberResponseDto(new PhoneNumberResponse(Collections.singletonList(new PhoneNumber())),
                new Meta());
        when(service.getAllPhoneNumbers(anyInt(),anyInt())).thenReturn(responseDto);
        mvc.perform(MockMvcRequestBuilders

                        .get("/phone-bank/phone-numbers")
                        .param("pageNo","-1")
                        .param("pageSize","10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].message").exists());;

    }

    @Test
    void getPhoneNumbers_InvalidPageSize_ThrowsBadRequest() throws Exception
    {
        PhoneNumberResponseDto responseDto =  new PhoneNumberResponseDto(new PhoneNumberResponse(Collections.singletonList(new PhoneNumber())),
                new Meta());
        when(service.getAllPhoneNumbers(anyInt(),anyInt())).thenReturn(responseDto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/phone-numbers")
                        .param("pageNo","0")
                        .param("pageSize","-10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].message").exists());;

    }


    @Test
    void getPhoneNumberById_returnOKResponse() throws Exception
    {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.getPhoneNumberById(anyLong())).thenReturn(phoneNumber);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/phone-numbers/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

    }

    @Test
    void getPhoneNumberById_InvalidId_ThrowsBadRequest() throws Exception {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.getPhoneNumberById(anyLong())).thenReturn(phoneNumber);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/phone-numbers/-2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].message").exists());
    }

    @Test
    void getPhoneNumberById_NonNumericId_ThrowsBadRequest() throws Exception {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.getPhoneNumberById(anyLong())).thenReturn(phoneNumber);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/phone-numbers/xyz")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }

    @Test
    void getPhoneNumberById_nonExistingId_ThrowsResourceNotFoundException() throws Exception {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.getPhoneNumberById(anyLong())).thenThrow(ResourceNotFoundException.class);
        mvc.perform(MockMvcRequestBuilders
                        .get("/phone-bank/phone-numbers/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").exists());;
    }


    @Test
    void updateActivation_returnOKResponse() throws Exception
    {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.updateActivation(anyLong(),anyBoolean())).thenReturn(phoneNumber);
        mvc.perform(MockMvcRequestBuilders
                        .put("/phone-bank/phone-numbers/activate/1/true")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

    }

    @Test
    void updateActivation_InvalidStatus_ThrowsBadRequest() throws Exception
    {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.updateActivation(anyLong(),anyBoolean())).thenReturn(phoneNumber);
        mvc.perform(MockMvcRequestBuilders
                        .put("/phone-bank/phone-numbers/activate/1/trueeeeeeeeeeeeeeeeeeeeee")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());

    }

    @Test
    void updateActivation_InvalidId_ThrowsBadRequest() throws Exception
    {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.updateActivation(anyLong(),anyBoolean())).thenReturn(phoneNumber);
        mvc.perform(MockMvcRequestBuilders
                        .put("/phone-bank/phone-numbers/activate/-1/true")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].message").exists());

    }

    @Test
    void updateActivation_nonExistent_ThrowsResourceNotFoundException() throws Exception
    {
        PhoneNumber phoneNumber =  new PhoneNumber();
        when(service.updateActivation(anyLong(),anyBoolean())).thenThrow(ResourceNotFoundException.class);
        mvc.perform(MockMvcRequestBuilders
                        .put("/phone-bank/phone-numbers/activate/200/true")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").exists());;

    }

}
