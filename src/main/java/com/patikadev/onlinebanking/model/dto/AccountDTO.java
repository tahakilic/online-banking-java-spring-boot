package com.patikadev.onlinebanking.model.dto;

import com.patikadev.onlinebanking.model.enums.AccountType;
import com.patikadev.onlinebanking.model.enums.CurrencyType;

public record AccountDTO(AccountType accountType,
                         CurrencyType currencyType,
                         String bankCode,
                         String branchCode,
                         Long accountNumber,
                         String iban) {

}
