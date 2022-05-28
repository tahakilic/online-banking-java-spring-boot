package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.CustomerAddressConverter;
import com.patikadev.onlinebanking.converter.CustomerConverter;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.entity.CustomerAddress;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerAddressResponse;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import com.patikadev.onlinebanking.repository.AccountRepository;
import com.patikadev.onlinebanking.repository.CustomerAddressRepository;
import com.patikadev.onlinebanking.repository.CustomerRepository;
import com.patikadev.onlinebanking.service.CustomerService;
import com.patikadev.onlinebanking.validator.IbanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerConverter customerConverter;
    private final CustomerAddressConverter customerAddressConverter;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;
    private final IbanValidator ibanValidator;
    private final AccountRepository accountRepository;

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
        ibanValidator.validate(customerRequest.account().iban());

        if(!(Objects.isNull(accountRepository.findByIban(customerRequest.account().iban())))){
            throw new ServiceOperationException.AccountNotValidException("No more than one is created from the same iban!");
        }
        if(!(Objects.isNull(customerRepository.findByIdentityNumber(customerRequest.identityNumber())))){
            throw new ServiceOperationException.AccountNotValidException("No more than one is created from the same identityNumber!");
        }
        Customer save=customerRepository.save(customer);
        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Transactional
    @Override
    public String updateCustomer(UpdateCustomerRequest customerRequest) {
        Customer customer=customerRepository.findById(customerRequest.id())
                .orElseThrow(()-> new ServiceOperationException.CustomerNotValidException("Customer not found!"));

        Customer newCustomer=customerConverter.updateCustomerRequestToCustomer(customer,customerRequest);
        Customer save = customerRepository.save(newCustomer);
        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Override
    public String deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        List<Account> byCustomer = accountRepository.findByCustomer(customer);
        for(Account account:byCustomer){
            if(account.getBalance().compareTo(new BigDecimal(0))!=0){
                throw new ServiceOperationException.AccountNotValidException("Account balance is not empty!");
            }
        }

        customerRepository.deleteById(id);
        return "successful";
    }

    @Override
    public String updateCustomerAddress(Long id, CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = customerAddressRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer Address not found!"));

        CustomerAddress newCustomerAddress=customerAddressConverter.customerAddressDTOToCustomerAddress(customerAddress,customerAddressDTO);
        CustomerAddress save = customerAddressRepository.save(newCustomerAddress);
        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Override
    public List<CustomerAddressResponse> getCustomerAllAddress(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        List<CustomerAddress> customerAddressList = customerAddressRepository.findByCustomer(customer);
        List<CustomerAddressResponse> customerAddressResponsesList = customerAddressConverter.customerAddressListToCustomerAddressResponseList(customerAddressList);
        return customerAddressResponsesList;

    }

    @Override
    public CustomerAddressResponse getAddress(Long customerAddressId) {
        CustomerAddress customerAddress = customerAddressRepository.findById(customerAddressId).orElseThrow(() -> new ServiceOperationException.CustomerAddressNotValidException("Customer Address not found!"));
        CustomerAddressResponse customerAddressResponse = customerAddressConverter.customerAddressToCustomerAddressResponse(customerAddress);
        return customerAddressResponse;
    }

    @Override
    public String addCustomerAddress(Long id,CustomerAddressDTO customerAddressDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        CustomerAddress customerAddress = customerAddressConverter.customerAddressDTOToCustomerAddress(customer, customerAddressDTO);

        CustomerAddress save = customerAddressRepository.save(customerAddress);
        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Override
    public String deleteCustomerAddress(Long customerAddressId) {
        customerAddressRepository.findById(customerAddressId)
                .orElseThrow(()-> new ServiceOperationException.CustomerAddressNotValidException("Customer address not found!"));
        customerAddressRepository.deleteById(customerAddressId);
        return "successful";
    }
}
