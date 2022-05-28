package com.patikadev.onlinebanking.model.response;

import com.patikadev.onlinebanking.model.enums.CardStatus;
import com.patikadev.onlinebanking.model.enums.CardType;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;

import java.math.BigDecimal;
import java.util.Date;

public record CardResponse(Long id,
                           String cardNumber,
                           String cardSecurityNumber,
                           String cardExpirationDate,
                           String cardPassword,
                           BigDecimal balance,
                           CurrencyCode currencyCode,
                           BigDecimal cardLimit,
                           BigDecimal currentLimit,
                           Date creatAt,
                           CardStatus cardStatus,
                           CardType cardType) {
}
