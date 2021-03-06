package com.patikadev.onlinebanking.converter;

import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.ShoppingWithCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.model.response.CreditCardDebtResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CardConverter {
    Card createCardRequestToCard(Customer customer, CreateCardRequest createCardRequest);

    CardResponse cardToCardResponse(Card card);

    List<CardResponse> cardListToCardResponseList(List<Card> cards);

    Card updateCardLimits(Card card,UpdateCardLimitRequest updateCardLimitRequest);

    Card updateCardPassword(Card card, UpdateCardPasswordRequest updateCardPasswordRequest);

    CreditCardDebtResponse cardToCreditCardDebtResponse(Card creditCard);

    Card atmPayToCard(BigDecimal amount, Card card);

    Card shoppingWithCard(Card card, ShoppingWithCardRequest shoppingWithCardRequest);
}
