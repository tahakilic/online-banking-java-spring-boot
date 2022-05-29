package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.request.TransferRequest;
import com.patikadev.onlinebanking.model.response.CreditCardDebtResponse;

import java.math.BigDecimal;

public interface TransferService {
    String accountToAccount(String fromAccount, String toAccount, TransferRequest transferRequest,BigDecimal amount);

    String accountToCard(BigDecimal amount, String fromAccountIban, Long toCardNumber);

    String cardToAccount(Long fromCardNumber, String toAccountIban, BigDecimal amount);
}
