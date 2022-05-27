package com.patikadev.onlinebanking.model.dto;

import com.patikadev.onlinebanking.model.enums.AccountType;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;

public record AccountDTO(AccountType accountType,
                         CurrencyCode currencyCode,
                         String bankCode,
                         String branchCode,
                         Long accountNumber,
                         String iban) {

}
