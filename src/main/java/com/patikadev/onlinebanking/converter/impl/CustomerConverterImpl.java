package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.CustomerConverter;
import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.dto.ContactInformationDTO;
import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.entity.*;
import com.patikadev.onlinebanking.model.enums.AccountStatus;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomerConverterImpl implements CustomerConverter {

    @Override
    public CustomerResponse customerToCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getMiddleName(),
                customer.getLastName(),
                customer.getIdentityNumber(),
                customer.getBirthDay(),
                customer.getGender(),
                contactInformationToContactInformationDTO(customer.getContactInformation())

        );
        return customerResponse;
    }


    @Override
    public Customer createCustomerRequestToCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer();
        customer.setName(createCustomerRequest.name());
        customer.setMiddleName(createCustomerRequest.middleName());
        customer.setLastName(createCustomerRequest.lastName());
        customer.setIdentityNumber(createCustomerRequest.identityNumber());
        customer.setBirthDay(createCustomerRequest.birthDay());
        customer.setGender(createCustomerRequest.gender());
        customer.setContactInformation(contactInformationDTOToContactInformation(createCustomerRequest.contactInformation()));

        customer.addAddress(customerAddressDTOToCustomerAddress(createCustomerRequest.customerAddress()));

        customer.addAccount(accountDTOToAccount(createCustomerRequest.account()));
        return customer;
    }

    @Override
    public Customer updateCustomerRequestToCustomer(Customer customer,UpdateCustomerRequest customerRequest) {
        customer.setName(customerRequest.name());
        customer.setMiddleName(customerRequest.middleName());
        customer.setLastName(customerRequest.lastName());
        customer.setIdentityNumber(customerRequest.identityNumber());
        customer.setBirthDay(customerRequest.birthDay());
        customer.setGender(customerRequest.gender());
        customer.setContactInformation(contactInformationDTOToContactInformation(customerRequest.contactInformation()));

        return customer;
    }

    public Account accountDTOToAccount(AccountDTO accountDTO) {
        Account account=new Account();
        account.setAccountType(accountDTO.accountType());
        account.setAccountNumber(accountDTO.accountNumber());
        account.setCurrency(currencyDTOToCurrency(accountDTO.currency()));
        account.setBankCode(accountDTO.bankCode());
        account.setBranchCode(accountDTO.branchCode());
        account.setAccountNumber(accountDTO.accountNumber());
        account.setIban(accountDTO.iban());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(new Date());
        return account;
    }

    public Currency currencyDTOToCurrency(CurrencyDTO currencyDTO) {
        Currency currency= new Currency();
        currency.setName(currencyDTO.name());
        currency.setCode(currencyDTO.code());
        currency.setSymbol(currencyDTO.symbol());
        return currency;
    }

    public CustomerAddress customerAddressDTOToCustomerAddress(CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCountry(customerAddressDTO.country());
        customerAddress.setCity(customerAddressDTO.city());
        customerAddress.setDistrict(customerAddressDTO.district());
        customerAddress.setAddressType(customerAddressDTO.addressType());
        customerAddress.setDescription(customerAddressDTO.description());
        return customerAddress;
    }

    public ContactInformation contactInformationDTOToContactInformation(ContactInformationDTO contactInformationDTO){
        ContactInformation contactInformation=new ContactInformation();
        contactInformation.setPrimaryEmail(contactInformationDTO.primaryEmail());
        contactInformation.setSecondaryEmail(contactInformationDTO.secondaryEmail());
        contactInformation.setPrimaryPhoneNumber(contactInformationDTO.primaryPhoneNumber());
        contactInformation.setSecondaryPhoneNumber(contactInformationDTO.secondaryPhoneNumber());
        return contactInformation;
    }


    private ContactInformationDTO  contactInformationToContactInformationDTO(ContactInformation contactInformation) {
        return  new ContactInformationDTO(
                contactInformation.getPrimaryEmail(),
                contactInformation.getSecondaryEmail(),
                contactInformation.getPrimaryPhoneNumber(),
                contactInformation.getSecondaryPhoneNumber()
        );

    }


}
