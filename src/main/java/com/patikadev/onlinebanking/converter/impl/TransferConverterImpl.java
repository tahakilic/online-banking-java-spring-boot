package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.TransferConverter;
import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.TransferAccount;
import com.patikadev.onlinebanking.model.request.TransferRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class TransferConverterImpl implements TransferConverter {
    @Override
    public Account toAccountUpdateBalance(Account toAccount, BigDecimal amount) {
        toAccount.setBalance(toAccount.getBalance().add(amount));
        return toAccount;
    }

    @Override
    public Account fromAccountUpdateBalance(Account fromAccount, BigDecimal amount) {
        fromAccount.setLockedBalance(amount);
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        return fromAccount;
    }

    @Override
    public TransferAccount toTransferAccount(Account toNewAccount, Account fromNewAccount, TransferRequest transferRequest, CurrencyDTO currencyDTO) {
        TransferAccount transferAccount=new TransferAccount();
        transferAccount.setToAccount(toNewAccount);
        transferAccount.setFromAccount(fromNewAccount);
        transferAccount.setAmount(currencyDTO.result());
        transferAccount.setFromCurrencyCode(fromNewAccount.getCurrencyCode());
        transferAccount.setToCurrencyCode(toNewAccount.getCurrencyCode());
        transferAccount.setExchangeRate(currencyDTO.info().rate());
        transferAccount.setDescription(transferRequest.description());
        transferAccount.setCreatedAt(new Date());

        return transferAccount;
    }
}
