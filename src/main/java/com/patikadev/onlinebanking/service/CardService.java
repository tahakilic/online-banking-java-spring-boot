package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.ShoppingWithCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.model.response.CreditCardDebtResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CardService  {
    String createCard(Long customerId, CreateCardRequest createCardRequest);

    CardResponse getCard(Long cardId);

    List<CardResponse> getCustomerAllCards(Long customerId);

    String updateCardLimits(Long cardId,UpdateCardLimitRequest updateCardLimitRequest);

    String updateCardPassword(Long cardId, UpdateCardPasswordRequest updateCardPasswordRequest);

    String deleteCard(Long cardId);

    CreditCardDebtResponse getCreditCardDebt(Long cardNumber);

    String atmPayToCard(BigDecimal amount, Long cardNumber);

    String shoppingWithCard(ShoppingWithCardRequest shoppingWithCardRequest);
}
