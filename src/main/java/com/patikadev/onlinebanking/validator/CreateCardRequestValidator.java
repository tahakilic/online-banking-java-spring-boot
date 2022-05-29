package com.patikadev.onlinebanking.validator;

import com.patikadev.onlinebanking.exception.BaseException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.enums.CardType;
import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CreateCardRequestValidator implements Validator<CreateCardRequest>{
    @Override
    public void validate(CreateCardRequest createCardRequest) throws BaseException {
        if(Objects.isNull(createCardRequest)){
            throw new ValidationOperationException.CardNotValidException("Card can not be null or empty!");
        }
        if(!(StringUtils.hasLength(createCardRequest.cardSecurityNumber()))){
            throw new ValidationOperationException.CardNotValidException("Card 'cardSecurityNumber' can not be null or empty!");
        }
        if(!(StringUtils.hasLength(createCardRequest.cardExpirationDate()))){
            throw new ValidationOperationException.CardNotValidException("Card 'cardExpirationDate' can not be null or empty!");
        }
        if(!(StringUtils.hasLength(createCardRequest.cardPassword()))){
            throw new ValidationOperationException.CardNotValidException("Card 'cardPassword' can not be null or empty!");
        }
        if(createCardRequest.cardLimit()!=null && createCardRequest.cardLimit().compareTo(new BigDecimal(0))<=0){
            throw new ValidationOperationException.CardNotValidException("Card 'cardLimit' can not be negative or zero!");
        }
        if(createCardRequest.currentLimit()!=null && createCardRequest.currentLimit().compareTo(new BigDecimal(0))<=0){
            throw new ValidationOperationException.CardNotValidException("Card 'currentLimit' can not be negative or zero!");
        }
        if(Objects.isNull(createCardRequest.cardType())){
            throw new ValidationOperationException.CardNotValidException("Card 'cardType' can not be null or empty!");
        }
        if(createCardRequest.cardType()== CardType.CREDIT_CARD && createCardRequest.cardLimit()==null){
            throw new ValidationOperationException.CardNotValidException("Credit card 'cardLimit' is mandatory!");
        }
        if(createCardRequest.cardType()==CardType.BANK_CARD && createCardRequest.cardLimit()!=null){
            throw new ValidationOperationException.CardNotValidException("No limit can be placed on the bank card!");
        }

        if(createCardRequest.cardSecurityNumber().length()!=3){
            throw new ValidationOperationException.CardNotValidException("cardSecurityNumber must be three digits!");
        }
    }
}
