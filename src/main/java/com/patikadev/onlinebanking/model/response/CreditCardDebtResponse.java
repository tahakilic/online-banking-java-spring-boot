package com.patikadev.onlinebanking.model.response;

import java.math.BigDecimal;

public record CreditCardDebtResponse(Long creditCardNumber,
                                     BigDecimal creditCardBalance,
                                     BigDecimal creditCardDebt,
                                     BigDecimal creditCardLimit) {
}
