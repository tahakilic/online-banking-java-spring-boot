package com.patikadev.onlinebanking.model.request;

import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.dto.ContactInformationDTO;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.enums.Gender;

import java.util.Date;


public record CreateCustomerRequest(String name,
                                    String middleName,
                                    String lastName,
                                    Long identityNumber,
                                    Date birthDay,
                                    Gender gender,
                                    ContactInformationDTO contactInformation,
                                    CustomerAddressDTO customerAddress,
                                    AccountDTO account) {

}
