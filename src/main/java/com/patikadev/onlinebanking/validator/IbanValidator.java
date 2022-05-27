package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class IbanValidator implements Validator<String>{
    @Override
    public void validate(String iban) throws BaseException {
        if (!(StringUtils.hasLength(iban))) {
            throw new ValidationOperationException.IbanNotValidException("iban can not be null or empty!");
        }
        if(!(isIban(iban))){
            throw new ValidationOperationException.IbanNotValidException("iban is not in the correct format!");
        }

    }

    public boolean isIban(String iban){
        String regex = "^TR\\d{8}[A-Z0-9]{16}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher= pattern.matcher(iban);
        return matcher.matches();
    }
}
