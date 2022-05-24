package com.patikadev.onlinebanking.model.dto;

import com.patikadev.onlinebanking.model.enums.AccountType;

public record AccountDTO(AccountType accountType,
                         CurrencyDTO currency,
                         String bankCode,
                         String branchCode,
                         Long accountNumber,
                         String iban) {

}
