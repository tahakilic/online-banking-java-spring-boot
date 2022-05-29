package com.patikadev.onlinebanking.facade;

import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;
import com.patikadev.onlinebanking.model.request.TransferRequest;

import java.math.BigDecimal;

public interface TransferFacade {
    CurrencyDTO currencyControl(CurrencyCode fromCurrency, CurrencyCode toCurrency, BigDecimal amount);

    void accountToAccountException(Account fromAccount, Account toAccount, TransferRequest transferRequest,BigDecimal amount);

    void accountToCardException(Account fromAccount, Card toCard, BigDecimal amount);

    void cardToAccountException(Card fromCard, Account toAccount, BigDecimal amount);
}
