package com.patikadev.onlinebanking.model.request;

import com.patikadev.onlinebanking.model.enums.CardType;

import java.math.BigDecimal;

public record CreateCardRequest(Long cardNumber,
                                String cardSecurityNumber,
                                String cardExpirationDate,
                                String cardPassword,
                                BigDecimal cardLimit,
                                BigDecimal currentLimit,
                                CardType cardType) {
}
