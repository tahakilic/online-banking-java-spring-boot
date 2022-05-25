package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.CustomerAddressConverter;
import com.patikadev.onlinebanking.converter.CustomerConverter;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.entity.CustomerAddress;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import com.patikadev.onlinebanking.repository.CustomerAddressRepository;
import com.patikadev.onlinebanking.repository.CustomerRepository;
import com.patikadev.onlinebanking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerConverter customerConverter;
    private final CustomerAddressConverter customerAddressConverter;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    @Override
    public CustomerResponse getCustomer(Long id) {
        Customer customer=customerRepository.findById(id)
                .orElseThrow(()-> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        return customerConverter.customerToCustomerResponse(customer);
    }

    @Transactional
    @Override
    public String createCustomer(CreateCustomerRequest customerRequest) {
        Customer customer=customerConverter.createCustomerRequestToCustomer(customerRequest);
        Customer save=customerRepository.save(customer);
        return  !(save.getId().equals(null))?"successful":"unsuccessful";
    }

    @Transactional
    @Override
    public String updateCustomer(UpdateCustomerRequest customerRequest) {
        Customer customer=customerRepository.findById(customerRequest.id())
                .orElseThrow(()-> new ServiceOperationException.CustomerNotValidException("Customer not found!"));

        Customer newCustomer=customerConverter.updateCustomerRequestToCustomer(customer,customerRequest);
        Customer save = customerRepository.save(newCustomer);
        return !(save.getId().equals(null))?"successful":"unsuccessful";
    }

    @Override
    public String deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(()-> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        customerRepository.deleteById(id);
        return "successful";
    }

    @Override
    public String updateCustomerAddress(Long id, CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = customerAddressRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer Address not found!"));

        CustomerAddress newCustomerAddress=customerAddressConverter.customerAddressDTOToCustomerAddress(customerAddress,customerAddressDTO);
        CustomerAddress save = customerAddressRepository.save(newCustomerAddress);
        return !(save.getId().equals(null))?"successful":"unsuccessful";
    }

    @Override
    public List<CustomerAddressDTO> getCustomerAllAddress(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        List<CustomerAddress> customerAddressList = customerAddressRepository.findByCustomer(customer);
        List<CustomerAddressDTO> customerAddressDTOList = customerAddressConverter.customerAddressListToCustomerAddressDTOList(customerAddressList);
        return customerAddressDTOList;

    }

    @Override
    public String addCustomerAddress(Long id,CustomerAddressDTO customerAddressDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        CustomerAddress customerAddress = customerAddressConverter.customerAddressDTOToCustomerAddress(customer, customerAddressDTO);

        CustomerAddress save = customerAddressRepository.save(customerAddress);
        return !(save.getId().equals(null))?"successful":"unsuccessful";
    }
}
