package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class UpdateCardLimitRequestValidator implements Validator<UpdateCardLimitRequest>{
    @Override
    public void validate(UpdateCardLimitRequest updateCardLimitRequest) throws BaseException {
        if(Objects.isNull(updateCardLimitRequest)){
            throw new ValidationOperationException.CardNotValidException("updateCardLimitRequest can not be null or empty!");
        }
        if(Objects.isNull(updateCardLimitRequest.currentLimit())){
            throw new ValidationOperationException.CardNotValidException(" currentLimit can not be null or empty!");
        }

    }
}
