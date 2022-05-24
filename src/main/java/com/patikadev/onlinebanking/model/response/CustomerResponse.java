package com.patikadev.onlinebanking.model.response;

import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.enums.Gender;

public record CustomerResponse(Long id,
                               String firstName,
                               String lastName,
                               String email,
                               String phoneNumber,
                               Gender gender,
                               CustomerAddressDTO customerAddressDTO) {

}
