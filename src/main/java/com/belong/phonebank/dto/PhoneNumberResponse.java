package com.belong.phonebank.dto;

import com.belong.phonebank.model.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PhoneNumberResponse {
    List<PhoneNumber> phoneNumbers;
}
