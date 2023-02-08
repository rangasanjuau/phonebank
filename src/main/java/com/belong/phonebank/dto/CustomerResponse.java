package com.belong.phonebank.dto;

import com.belong.phonebank.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
    Customer customer;
}
