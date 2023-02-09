package com.belong.phonebank.controller;


import com.belong.phonebank.exception.ResourceNotFoundException;
import com.belong.phonebank.dto.PhoneNumberResponseDto;
import com.belong.phonebank.model.PhoneNumber;
import com.belong.phonebank.service.PhoneNumberService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phone-bank/phone-numbers")
@Validated
@CrossOrigin(origins = "https://editor.swagger.io")
public class PhoneNumberController {
    @Autowired
    public PhoneNumberService service;


    /************************************************************************************************
     * http://localhost:8080/phone-bank/phone-numbers
     *
     * @param
     * @return PhoneNumberResponseDto
     * @throws
     */
    @GetMapping()
    public PhoneNumberResponseDto getPhoneNumbers(@RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number must be greater than or equal to 0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be greater than or equal to 1")
                                                  @Max(value = 100, message = "Page size must be less than or equal to 100") Integer pageSize) {
        return service.getAllPhoneNumbers(pageNo, pageSize);
    }


    /************************************************************************************************
     * http://localhost:8080/phone-bank/phone-numbers/{id}
     *
     * @param
     * @return PhoneNumber
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public PhoneNumber getPhoneNumberById(@PathVariable @Positive(message ="Invalid Phone Id") Long id) throws ResourceNotFoundException {
        return service.getPhoneNumberById(id);
    }


    /************************************************************************************************
     * http://localhost:8080/phone-bank/phone-numbers/activate/{id}/{status}
     *
     * @param
     * @return PhoneNumber
     * @throws ResourceNotFoundException
     */

    
    @PutMapping("/activate/{id}/{status}")
    public PhoneNumber updateActivation(@PathVariable @Positive(message ="Invalid Phone Id") Long id, @PathVariable boolean status) throws ResourceNotFoundException {
        return service.updateActivation(id, status);
    }


}
