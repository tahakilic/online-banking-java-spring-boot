package com.patikadev.onlinebanking.model.request;

import com.patikadev.onlinebanking.model.enums.CardStatus;
import com.patikadev.onlinebanking.model.enums.CardType;

import java.math.BigDecimal;

public record CreateCardRequest(String cardNumber,
                                String cardSecurityNumber,
                                String cardExpirationDate,
                                String cardPassword,
                                BigDecimal cardLimit,
                                BigDecimal currentLimit,
                                CardStatus cardStatus,
                                CardType cardType) {
}
