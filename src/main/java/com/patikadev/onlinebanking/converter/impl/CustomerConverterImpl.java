package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.CustomerConverter;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.entity.CustomerAddress;
import com.patikadev.onlinebanking.model.request.CustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverterImpl implements CustomerConverter {
    @Override
    public CustomerResponse customerToCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getGender(),
                new CustomerAddressDTO(customer.getCustomerAddress().getPhoneNumber(),
                        customer.getCustomerAddress().getCountry(),
                        customer.getCustomerAddress().getCity(),
                        customer.getCustomerAddress().getPostalCode(),
                        customer.getCustomerAddress().getDescription())
        );
        return customerResponse;
    }

    @Override
    public Customer customerRequestToCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setEmail(customerRequest.email());
        customer.setGender(customerRequest.gender());
        customer.setPhoneNumber(customerRequest.phoneNumber());
        customer.setCustomerAddress(customerAddressDTOToCustomerAddress(customerRequest.customerAddressDTO()));

        return customer;
    }

    public CustomerAddress customerAddressDTOToCustomerAddress(CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCountry(customerAddressDTO.country());
        customerAddress.setCity(customerAddressDTO.city());
        customerAddress.setPhoneNumber(customerAddressDTO.phoneNumber());
        customerAddress.setPostalCode(customerAddressDTO.postalCode());
        customerAddress.setDescription(customerAddressDTO.description());
        return customerAddress;
    }
}
