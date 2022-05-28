package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
@Component
public class UpdateCardPasswordRequestValidator implements Validator<UpdateCardPasswordRequest>{
    @Override
    public void validate(UpdateCardPasswordRequest updateCardPasswordRequest) throws BaseException {
        if(Objects.isNull(updateCardPasswordRequest)){
            throw new ValidationOperationException.CardNotValidException("updateCardPasswordRequest can not be null or empty!");
        }
        if(!(StringUtils.hasLength(updateCardPasswordRequest.password()))){
            throw new ValidationOperationException.CardNotValidException("Card password can not be null or empty!");
        }
    }
}
