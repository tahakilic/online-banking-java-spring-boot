package com.patikadev.onlinebanking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class ContactInformation {
    @Column(nullable = false)
    private String primaryEmail;
    private String secondaryEmail;
    @Column(nullable = false)
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
}