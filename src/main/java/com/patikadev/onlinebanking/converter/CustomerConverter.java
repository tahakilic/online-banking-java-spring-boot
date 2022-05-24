package com.patikadev.onlinebanking.converter;

import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.request.CustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;

public interface CustomerConverter {
    CustomerResponse customerToCustomerResponse(Customer customer);

    Customer customerRequestToCustomer(CustomerRequest customerRequest);
}
