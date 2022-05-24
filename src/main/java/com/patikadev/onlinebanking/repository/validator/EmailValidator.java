package com.patikadev.onlinebanking.repository.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator<String>{
    @Override
    public void validate(String email) throws BaseException {
        if(!(isEmail(email))){
            throw new ValidationOperationException.EmailFormatIsIncorrectException("Customer primaryEmail is not in the correct format!");
        }
    }

    public boolean isEmail(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }
}
