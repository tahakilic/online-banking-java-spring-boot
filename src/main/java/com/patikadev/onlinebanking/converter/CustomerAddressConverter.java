package com.patikadev.onlinebanking.converter;

import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.entity.CustomerAddress;

import java.util.List;

public interface CustomerAddressConverter {
    CustomerAddress customerAddressDTOToCustomerAddress(CustomerAddress customerAddress, CustomerAddressDTO customerAddressDTO);
    CustomerAddress customerAddressDTOToCustomerAddress(Customer customer, CustomerAddressDTO customerAddressDTO);

    List<CustomerAddressDTO> customerAddressListToCustomerAddressDTOList(List<CustomerAddress> customerAddressList);
}
