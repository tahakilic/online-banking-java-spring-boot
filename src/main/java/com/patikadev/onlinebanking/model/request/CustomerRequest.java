package com.patikadev.onlinebanking.model.request;

import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.enums.Gender;


public record CustomerRequest(String firstName,
                              String lastName,
                              String email,
                              String phoneNumber,
                              Gender gender,
                              CustomerAddressDTO customerAddressDTO) {

}
