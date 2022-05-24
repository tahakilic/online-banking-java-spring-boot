package com.patikadev.onlinebanking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CustomerAddress extends BaseEntity {

    private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;

    @ManyToOne
    private Customer customer;
}
