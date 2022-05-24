package com.patikadev.onlinebanking.repository.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class UpdateCustomerRequestValidator implements Validator<UpdateCustomerRequest>{
    @Override
    public void validate(UpdateCustomerRequest updatecustomerRequest) throws BaseException {
        if (Objects.isNull(updatecustomerRequest)){
            throw new ValidationOperationException.CustomerNotValidException("Customer updating can not be null or empty!");
        }
        if (!(StringUtils.hasLength(updatecustomerRequest.name()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer updating name can not be null or empty!");
        }
        if (!(StringUtils.hasLength(updatecustomerRequest.lastName()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer updating lastName can not be null or empty!");
        }
        if (!(StringUtils.hasLength(updatecustomerRequest.contactInformation().primaryEmail()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer updating primaryEmail can not be null or empty!");
        }
        if (!(StringUtils.hasLength(updatecustomerRequest.contactInformation().primaryPhoneNumber()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer updating primaryPhoneNumber can not be null or empty!");
        }
        if (Objects.isNull(updatecustomerRequest.gender())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer updating gender can not be null or empty!");
        }
        if (Objects.isNull(updatecustomerRequest.birthDay())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer updating birthDay can not be null or empty!");
        }
    }
}
