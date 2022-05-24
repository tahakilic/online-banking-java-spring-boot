package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getCustomer(Long id);

    CustomerResponse createCustomer(CreateCustomerRequest customerRequest);
}
