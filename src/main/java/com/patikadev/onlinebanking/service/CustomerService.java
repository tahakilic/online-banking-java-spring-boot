package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getCustomer(Long id);

    String createCustomer(CreateCustomerRequest customerRequest);

    String updateCustomer(UpdateCustomerRequest customerRequest);

    String deleteCustomer(Long id);
}
