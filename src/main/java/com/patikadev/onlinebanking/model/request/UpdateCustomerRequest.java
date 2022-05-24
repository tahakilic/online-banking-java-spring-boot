package com.patikadev.onlinebanking.model.request;

import com.patikadev.onlinebanking.model.dto.ContactInformationDTO;
import com.patikadev.onlinebanking.model.enums.Gender;

import java.util.Date;

public record UpdateCustomerRequest(Long id,
                                    String name,
                                    String middleName,
                                    String lastName,
                                    Long identityNumber,
                                    Date birthDay,
                                    Gender gender,
                                    ContactInformationDTO contactInformation) {
}
