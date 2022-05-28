package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.CustomerAddressConverter;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.entity.CustomerAddress;
import com.patikadev.onlinebanking.model.response.CustomerAddressResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerAddressConverterImpl implements CustomerAddressConverter {
    @Override
    public CustomerAddress customerAddressDTOToCustomerAddress(CustomerAddress customerAddress, CustomerAddressDTO customerAddressDTO) {
        customerAddress.setCountry(customerAddressDTO.country());
        customerAddress.setCity(customerAddressDTO.city());
        customerAddress.setDistrict(customerAddressDTO.district());
        customerAddress.setAddressType(customerAddressDTO.addressType());
        customerAddress.setDescription(customerAddressDTO.description());
        return customerAddress;
    }

    @Override
    public List<CustomerAddressResponse> customerAddressListToCustomerAddressResponseList(List<CustomerAddress> customerAddressList) {
        List<CustomerAddressResponse> customerAddressDTOList = new ArrayList<>();
        for (CustomerAddress customerAddress : customerAddressList) {
            customerAddressDTOList.add(new CustomerAddressResponse(customerAddress.getId(), customerAddress.getCountry(),
                    customerAddress.getCity(),
                    customerAddress.getDistrict(),
                    customerAddress.getAddressType(),
                    customerAddress.getDescription()));
        }
        return customerAddressDTOList;

    }

    @Override
    public CustomerAddress customerAddressDTOToCustomerAddress(Customer customer, CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomer(customer);
        customerAddress.setAddressType(customerAddressDTO.addressType());
        customerAddress.setCountry(customerAddressDTO.country());
        customerAddress.setCity(customerAddressDTO.city());
        customerAddress.setDistrict(customerAddressDTO.district());
        customerAddress.setDescription(customerAddressDTO.description());
        return customerAddress;
    }

    @Override
    public CustomerAddressResponse customerAddressToCustomerAddressResponse(CustomerAddress customerAddress) {
        return new CustomerAddressResponse(customerAddress.getId(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getDistrict(),
                customerAddress.getAddressType(),
                customerAddress.getDescription());
    }
}
