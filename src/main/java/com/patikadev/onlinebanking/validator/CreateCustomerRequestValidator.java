package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        /*if (!(Gender.MALE.equals(customerRequest.gender())) && !(Gender.FEMALE.equals(customerRequest.gender())) && !(Gender.ODER.equals(customerRequest.gender()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer gender is not compatible. it must be MALE, FEMALE or ODER!");
        } ----------------Nasıl yapılır bakılacak*/

        if(!(isEmail(createcustomerRequest.contactInformation().primaryEmail()))){
            throw new ValidationOperationException.CustomerNotValidException("Customer primaryEmail is not in the correct format!");
        }
        if(!(isEmail(createcustomerRequest.contactInformation().secondaryEmail()))){
            throw new ValidationOperationException.CustomerNotValidException("Customer secondaryEmail is not in the correct format!");
        }

    }

    public boolean isEmail(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

}
