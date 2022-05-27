package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class AmountValidator implements Validator<BigDecimal>{
    @Override
    public void validate(BigDecimal amount) throws BaseException {
        if(Objects.isNull(amount)){
            throw new ValidationOperationException.AmountNotValidException("Amount can not be null or empty!");
        }
        if(amount.compareTo(new BigDecimal(0))==0){
            throw new ValidationOperationException.AmountNotValidException("Amount can not be zero!");
        }
        if(amount.compareTo(new BigDecimal(0))<1){
            throw new ValidationOperationException.AmountNotValidException("Amount can not be negative!");

        }
    }
}
