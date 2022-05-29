package com.patikadev.onlinebanking.facade.impl;

import com.patikadev.onlinebanking.exception.FacadeOperationException;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.facade.TransferFacade;
import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.enums.*;
import com.patikadev.onlinebanking.model.request.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransferFacadeImpl implements TransferFacade {
    private final RestTemplate restTemplate;
    @Override
    public CurrencyDTO currencyControl(CurrencyCode fromCurrency,CurrencyCode toCurrency, BigDecimal amount) {
        final String url = "https://api.apilayer.com/exchangerates_data/convert?to=" +toCurrency.toString()
                + "&from=" + fromCurrency.toString()
                + "&amount=" + amount.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "dmAvyZxsoS2vXUan6PhYHwUNrHm8Zbxd");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CurrencyDTO> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CurrencyDTO.class);
        CurrencyDTO currencyResponse = response.getBody();

        if(Objects.isNull(currencyResponse.result())){
            throw new FacadeOperationException.TransferNotValidException("error when converting currency!");
        }
        if(Objects.isNull(currencyResponse.info().rate())){
            throw new FacadeOperationException.TransferNotValidException("error when converting currency!");
        }
        return currencyResponse;
    }

    @Override
    public void accountToAccountException(Account fromAccount, Account toAccount, TransferRequest transferRequest,BigDecimal amount) {
        if(Objects.isNull(fromAccount)){
            throw new FacadeOperationException.TransferNotValidException("fromIban could not match the account!");
        }
        if(Objects.isNull(toAccount)){
            throw new FacadeOperationException.TransferNotValidException("toIban could not match the account!");
        }

        if (fromAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new FacadeOperationException.TransferNotValidException("Sender account is not active!");
        }
        if (toAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new FacadeOperationException.TransferNotValidException("Receiver account is not active!");
        }

        int res = fromAccount.getBalance().subtract(fromAccount.getLockedBalance()).compareTo(amount);
        if (res < 0) {
            throw new FacadeOperationException.TransferNotValidException("Insufficient balance!");
        }
        if (fromAccount.getAccountType() == AccountType.CHECKING_ACCOUNT && fromAccount.getCustomer() != toAccount.getCustomer()) {
            throw new FacadeOperationException.TransferNotValidException("It is a checking account, no transfer can be made!");
        }
    }

    @Override
    public void accountToCardException(Account fromAccount, Card toCard, BigDecimal amount) {
        if(Objects.isNull(fromAccount)){
            throw new FacadeOperationException.TransferNotValidException("fromAccountIban could not match the account!");
        }
        if(Objects.isNull(toCard)){
            throw new FacadeOperationException.TransferNotValidException("toCardNumber could not match the card!");
        }
        if (fromAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new FacadeOperationException.TransferNotValidException("Sender account is not active!");
        }
        if (toCard.getCardStatus() != CardStatus.ACTIVE) {
            throw new FacadeOperationException.TransferNotValidException("Receiver card is not active!");
        }

        int res = fromAccount.getBalance().subtract(fromAccount.getLockedBalance()).compareTo(amount);
        if (res < 0) {
            throw new FacadeOperationException.TransferNotValidException("Insufficient balance!");
        }
        if (fromAccount.getAccountType() == AccountType.CHECKING_ACCOUNT) {
            throw new FacadeOperationException.TransferNotValidException("It is a checking account, no transfer can be made!");
        }
        if(toCard.getCardType()== CardType.CREDIT_CARD && toCard.getCustomer()!=fromAccount.getCustomer()){
            throw new FacadeOperationException.TransferNotValidException("It is a different credit card, no transfer can be made!");
        }
        if(toCard.getCardType()== CardType.CREDIT_CARD && toCard.getBalance().compareTo(new BigDecimal(0))==0){
            throw new FacadeOperationException.TransferNotValidException("Credit card debt is not found!");
        }
        if(toCard.getCardType()== CardType.CREDIT_CARD && toCard.getBalance().compareTo(amount)<0){
            throw new FacadeOperationException.TransferNotValidException("More amount! Amount required for a credit card : "+toCard.getBalance());
        }


    }

    @Override
    public void cardToAccountException(Card fromCard, Account toAccount, BigDecimal amount) {
        if(Objects.isNull(fromCard)){
            throw new FacadeOperationException.TransferNotValidException("fromCard could not match the account!");
        }
        if(Objects.isNull(toAccount)){
            throw new FacadeOperationException.TransferNotValidException("toAccount could not match the card!");
        }
        if(fromCard.getCardType()==CardType.CREDIT_CARD){
            throw new FacadeOperationException.TransferNotValidException("Credit card transfers cannot be made");
        }
        if (fromCard.getCardStatus() != CardStatus.ACTIVE) {
            throw new FacadeOperationException.TransferNotValidException("Sender card is not active!");
        }
        if (toAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new FacadeOperationException.TransferNotValidException("Receiver account is not active!");
        }

        int res = fromCard.getBalance().compareTo(amount);
        if (res < 0) {
            throw new FacadeOperationException.TransferNotValidException("Insufficient balance!");
        }
        if(fromCard.getCurrentLimit().compareTo(amount)<0){
            throw new ServiceOperationException.CardNotValidException("'currentLimit' is overstep!");
        }


    }
}
