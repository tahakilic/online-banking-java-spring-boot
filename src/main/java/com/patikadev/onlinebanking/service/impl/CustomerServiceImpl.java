package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.CustomerConverter;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.request.CustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import com.patikadev.onlinebanking.repository.CustomerRepository;
import com.patikadev.onlinebanking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse getCustomer(Long id) {
        Customer customer=customerRepository.findById(id)
                .orElseThrow(()-> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        return customerConverter.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer=customerConverter.customerRequestToCustomer(customerRequest);
        Customer getCustomer=customerRepository.save(customer);
        return customerConverter.customerToCustomerResponse(getCustomer);
    }
}
