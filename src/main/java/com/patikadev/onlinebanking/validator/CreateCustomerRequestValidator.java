package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.enums.Gender;
import com.patikadev.onlinebanking.model.request.CustomerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CreateCustomerRequestValidator implements Validator<CustomerRequest> {
    @Override
    public void validate(CustomerRequest customerRequest) throws BaseException {
        if (Objects.isNull(customerRequest)) {
            throw new ValidationOperationException.CustomerNotValidException("Customer can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerRequest.firstName()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer firstName can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerRequest.lastName()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer lastName can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerRequest.email()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer email can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerRequest.phoneNumber()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer phoneNumber can not be null or empty!");
        }
        if (Objects.isNull(customerRequest.gender())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer gender can not be null or empty!");
        }
        if (Objects.isNull(customerRequest.customerAddressDTO())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerRequest.customerAddressDTO().phoneNumber()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address phoneNumber can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerRequest.customerAddressDTO().country()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address country can not be null or empty!");
        }
        if (!(StringUtils.hasLength(customerRequest.customerAddressDTO().city()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer address city can not be null or empty!");
        }
        /*if (!(Gender.MALE.equals(customerRequest.gender())) && !(Gender.FEMALE.equals(customerRequest.gender())) && !(Gender.ODER.equals(customerRequest.gender()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer gender is not compatible. it must be MALE, FEMALE or ODER!");
        } ----------------Nasıl yapılır bakılacak*/
        if(!(isEmail(customerRequest.email()))){
            throw new ValidationOperationException.CustomerNotValidException("Customer email is not in the correct format!");
        }

    }

    public boolean isEmail(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

}
