package com.patikadev.onlinebanking.model.response;

import com.patikadev.onlinebanking.model.enums.AddressType;

public record CustomerAddressResponse(Long id,
                                      String country,
                                      String city,
                                      String district,
                                      AddressType addressType,
                                      String description) {
}
