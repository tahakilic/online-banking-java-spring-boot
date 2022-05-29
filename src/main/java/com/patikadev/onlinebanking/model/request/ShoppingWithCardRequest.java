package com.patikadev.onlinebanking.model.request;

import java.math.BigDecimal;

public record ShoppingWithCardRequest(BigDecimal amount,
                                      Long cardNumber,
                                      String cardSecurityNumber,
                                      String cardExpirationDate,
                                      String cardPassword) {
}
