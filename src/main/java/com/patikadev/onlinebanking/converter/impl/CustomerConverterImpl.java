package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.CustomerConverter;
import com.patikadev.onlinebanking.model.dto.ContactInformationDTO;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.entity.ContactInformation;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.entity.CustomerAddress;
import com.patikadev.onlinebanking.model.enums.Gender;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomerConverterImpl implements CustomerConverter {
    private final AccountConverterImpl accountConverterImpl;

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
        toCustomer(customer,
                createCustomerRequest.name(),
                createCustomerRequest.middleName(),
                createCustomerRequest.lastName(),
                createCustomerRequest.identityNumber(),
                createCustomerRequest.birthDay(),
                createCustomerRequest.gender(),
                createCustomerRequest.contactInformation());
        customer.setCreatedAt(new Date());
        customer.addAddress(customerAddressDTOToCustomerAddress(createCustomerRequest.customerAddress()));

        customer.addAccount(AccountConverterImpl.accountDTOToAccount(createCustomerRequest.account()));
        return customer;
    }

    @Override
    public Customer updateCustomerRequestToCustomer(Customer customer, UpdateCustomerRequest customerRequest) {
        toCustomer(customer,
                customerRequest.name(),
                customerRequest.middleName(),
                customerRequest.lastName(),
                customerRequest.identityNumber(),
                customerRequest.birthDay(),
                customerRequest.gender(),
                customerRequest.contactInformation());

        return customer;
    }

    private Customer toCustomer(Customer customer,
                                String name,
                                String middleName,
                                String lastNane,
                                Long identityNumber,
                                Date birthDay,
                                Gender gender,
                                ContactInformationDTO contactInformationDTO) {
        customer.setName(name);
        customer.setMiddleName(middleName);
        customer.setLastName(lastNane);
        customer.setIdentityNumber(identityNumber);
        customer.setBirthDay(birthDay);
        customer.setGender(gender);
        customer.setContactInformation(contactInformationDTOToContactInformation(contactInformationDTO));
        return customer;

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

    public ContactInformation contactInformationDTOToContactInformation(ContactInformationDTO contactInformationDTO) {
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setPrimaryEmail(contactInformationDTO.primaryEmail());
        contactInformation.setSecondaryEmail(contactInformationDTO.secondaryEmail());
        contactInformation.setPrimaryPhoneNumber(contactInformationDTO.primaryPhoneNumber());
        contactInformation.setSecondaryPhoneNumber(contactInformationDTO.secondaryPhoneNumber());
        return contactInformation;
    }


    private ContactInformationDTO contactInformationToContactInformationDTO(ContactInformation contactInformation) {
        return new ContactInformationDTO(
                contactInformation.getPrimaryEmail(),
                contactInformation.getSecondaryEmail(),
                contactInformation.getPrimaryPhoneNumber(),
                contactInformation.getSecondaryPhoneNumber()
        );

    }


}
