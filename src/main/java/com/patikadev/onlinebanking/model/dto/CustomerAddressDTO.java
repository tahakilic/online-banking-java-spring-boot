package com.patikadev.onlinebanking.model.dto;

public record CustomerAddressDTO(String phoneNumber,
                                 String country,
                                 String city,
                                 String postalCode,
                                 String description) {
}
