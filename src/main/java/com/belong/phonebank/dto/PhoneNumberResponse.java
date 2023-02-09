package com.belong.phonebank.dto;

import com.belong.phonebank.model.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberResponse {
    List<PhoneNumber> phoneNumbers;
}
