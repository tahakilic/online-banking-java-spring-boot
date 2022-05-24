package com.patikadev.onlinebanking.model.dto;

import com.patikadev.onlinebanking.model.enums.AddressType;

public record CustomerAddressDTO(String country,
                                 String city,
                                 String district,
                                 AddressType addressType,
                                 String description) {
}
