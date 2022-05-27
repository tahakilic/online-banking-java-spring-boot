package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.request.TransferRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class TransferValidator implements Validator<TransferRequest>{
    @Override
    public void validate(TransferRequest transferRequest) throws BaseException {
        if(Objects.isNull(transferRequest)){
            throw new ValidationOperationException.TransferNotValidException("Transfer can not be null or empty!");
        }

    }
}
