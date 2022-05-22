package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.model.request.CustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import com.patikadev.onlinebanking.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerResponse getCustomer(Long id) {
        return null;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return null;
    }
}
