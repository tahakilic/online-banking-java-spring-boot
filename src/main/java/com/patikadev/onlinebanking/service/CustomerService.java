package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerAddressResponse;
import com.patikadev.onlinebanking.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse getCustomer(Long id);

    String createCustomer(CreateCustomerRequest customerRequest);

    String updateCustomer(UpdateCustomerRequest customerRequest);

    String deleteCustomer(Long id);

    String updateCustomerAddress(Long id,CustomerAddressDTO customerAddressDTO);

    List<CustomerAddressResponse> getCustomerAllAddress(Long id);

    String addCustomerAddress(Long id,CustomerAddressDTO customerAddressDTO);

    String deleteCustomerAddress(Long customerAddressId);

    CustomerAddressResponse getAddress(Long customerAddressId);
}
