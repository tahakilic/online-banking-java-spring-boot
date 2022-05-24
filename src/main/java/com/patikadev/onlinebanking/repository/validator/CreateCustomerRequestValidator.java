package com.patikadev.onlinebanking.repository.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateCustomerRequestValidator implements Validator<CreateCustomerRequest> {
    @Override
    public void validate(CreateCustomerRequest createcustomerRequest) throws BaseException {
        if (Objects.isNull(createcustomerRequest)) {
            throw new ValidationOperationException.CustomerNotValidException("Customer can not be null or empty!");
        }
        if (!(StringUtils.hasLength(createcustomerRequest.name()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer name can not be null or empty!");
        }
        if (!(StringUtils.hasLength(createcustomerRequest.lastName()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer lastName can not be null or empty!");
        }
        if (!(StringUtils.hasLength(createcustomerRequest.contactInformation().primaryEmail()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer primaryEmail can not be null or empty!");
        }
        if (!(StringUtils.hasLength(createcustomerRequest.contactInformation().primaryPhoneNumber()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer primaryPhoneNumber can not be null or empty!");
        }
        if (Objects.isNull(createcustomerRequest.gender())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer gender can not be null or empty!");
        }
        if (Objects.isNull(createcustomerRequest.birthDay())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer birthDay can not be null or empty!");
        }
        if (Objects.isNull(createcustomerRequest.customerAddress())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address can not be null or empty!");
        }
        if (!(StringUtils.hasLength(createcustomerRequest.customerAddress().country()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address country can not be null or empty!");
        }
        if (!(StringUtils.hasLength(createcustomerRequest.customerAddress().city()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address city can not be null or empty!");
        }
        if (!(StringUtils.hasLength(createcustomerRequest.customerAddress().district()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address district can not be null or empty!");
        }
        if (Objects.isNull(createcustomerRequest.account())) {
            throw new ValidationOperationException.AccountNotValidException("Account can not be null or empty!");
        }



    }



}
