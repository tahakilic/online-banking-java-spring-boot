package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.request.CustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getCustomer(Long id);

    CustomerResponse createCustomer(CustomerRequest customerRequest);
}
