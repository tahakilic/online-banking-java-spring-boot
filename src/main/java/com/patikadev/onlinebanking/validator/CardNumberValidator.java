package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CardNumberValidator implements Validator<Long>{
    @Override
    public void validate(Long cardNumber) throws BaseException {
        if(cardNumber<1){
            throw new ValidationOperationException.CardNotValidException("Credit cardNumber can not be zero or negative!");
        }

        if(!(isCardNumber(cardNumber))){
            throw new ValidationOperationException.IbanNotValidException("cardNumber is not in the correct format!");
        }
    }

    public boolean isCardNumber(Long cardNumber){
        String regex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher= pattern.matcher(cardNumber.toString());
        return matcher.matches();
    }
}
