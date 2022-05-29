package com.patikadev.onlinebanking.converter;

import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.TransferAccount;
import com.patikadev.onlinebanking.model.entity.TransferCard;
import com.patikadev.onlinebanking.model.request.TransferRequest;

import java.math.BigDecimal;

public interface TransferConverter {
    Account toAccountUpdateBalance( Account toAccount, BigDecimal amount);

    Account fromAccountUpdateBalance(Account fromAccount, BigDecimal amount);

    Card toCardUpdateBalance(Card toCard, BigDecimal amount);

    TransferAccount toTransferAccount(Account toNewAccount, Account fromNewAccount, TransferRequest transferRequest, CurrencyDTO currencyDTO);

    TransferCard toTransferCard(Card toNewCard, Account fromNewAccount, CurrencyDTO currencyDTO);


   Card fromCardUpdateBalance(Card fromCard, BigDecimal amount);

    TransferCard cardToAccount(Card fromNewCard, Account toNewAccount, CurrencyDTO currencyResponse);
}
