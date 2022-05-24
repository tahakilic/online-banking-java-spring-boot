package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.AddressType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CustomerAddress extends BaseEntity {
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String district;
    private AddressType addressType;
    private String description;

    @ManyToOne
    private Customer customer;
}
