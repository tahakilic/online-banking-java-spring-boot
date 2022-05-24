package com.patikadev.onlinebanking.converter;

import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;

public interface CustomerConverter {
    CustomerResponse customerToCustomerResponse(Customer customer);

    Customer createCustomerRequestToCustomer(CreateCustomerRequest customerRequest);

    Customer updateCustomerRequestToCustomer(Customer customer,UpdateCustomerRequest customerRequest);
}
