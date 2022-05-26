package com.patikadev.onlinebanking.model.response;

import com.patikadev.onlinebanking.model.enums.AccountStatus;
import com.patikadev.onlinebanking.model.enums.AccountType;
import com.patikadev.onlinebanking.model.enums.CurrencyType;

import java.math.BigDecimal;
import java.util.Date;

public record AccountResponse(Long id,
                              String iban,
                              String bankCode,
                              String branchCode,
                              Long accountNumber,
                              CurrencyType currencyType,
                              AccountStatus accountStatus,
                              AccountType accountType,
                              Date createdAt,
                              Date blockedAt,
                              Date canBeActiveAt,
                              BigDecimal balance,
                              BigDecimal lockedBalance) {
}
