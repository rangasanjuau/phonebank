package com.belong.phonebank.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="phonenumber")
public class PhoneNumber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long phoneNumberId;
    private String phoneNumber;
    private String type;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

}