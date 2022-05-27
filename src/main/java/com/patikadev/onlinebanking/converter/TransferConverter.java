package com.patikadev.onlinebanking.converter;

import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.TransferAccount;
import com.patikadev.onlinebanking.model.request.TransferRequest;

import java.math.BigDecimal;

public interface TransferConverter {
    Account toAccountUpdateBalance( Account toAccount, BigDecimal amount);

    Account fromAccountUpdateBalance(Account fromAccount, BigDecimal amount);

    TransferAccount toTransferAccount(Account toNewAccount, Account fromNewAccount, TransferRequest transferRequest, CurrencyDTO currencyDTO);
}
