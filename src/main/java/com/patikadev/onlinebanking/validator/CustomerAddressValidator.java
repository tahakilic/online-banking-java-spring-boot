package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CustomerAddressValidator implements Validator<CustomerAddressDTO> {
    @Override
    public void validate(CustomerAddressDTO customerAddressDTO) throws BaseException {
        if (Objects.isNull(customerAddressDTO)) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerAddressDTO.country()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address country can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerAddressDTO.city()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address city can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerAddressDTO.district()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address district can not be null or empty!");
        }
    }
}
