package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.CardConverter;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CardConverterImpl implements CardConverter {
    @Override
    public Card createCardRequestToCard(Customer customer, CreateCardRequest createCardRequest) {
        Card card = new Card();
        card.setCardNumber(createCardRequest.cardNumber());
        card.setCardSecurityNumber(createCardRequest.cardSecurityNumber());
        card.setCardExpirationDate(createCardRequest.cardExpirationDate());
        card.setCardPassword(createCardRequest.cardPassword());
        card.setCardLimit(createCardRequest.cardLimit());
        card.setCurrentLimit(createCardRequest.currentLimit());
        card.setCreatAt(new Date());
        card.setCardType(createCardRequest.cardType());
        card.setCustomer(customer);
        return card;
    }

    @Override
    public CardResponse cardToCardResponse(Card card) {
        return new CardResponse(card.getId(),
                card.getCardNumber(),
                card.getCardSecurityNumber(),
                card.getCardExpirationDate(),
                card.getCardPassword(),
                card.getBalance(),
                card.getCurrencyCode(),
                card.getCardLimit(),
                card.getCurrentLimit(),
                card.getCreatAt(),
                card.getCardStatus(),
                card.getCardType());
    }

    @Override
    public List<CardResponse> cardListToCardResponseList(List<Card> cards) {
        List<CardResponse> cardResponseList = new ArrayList<>();
        for (Card card : cards) {
            cardResponseList.add(new CardResponse(card.getId(),
                    card.getCardNumber(),
                    card.getCardSecurityNumber(),
                    card.getCardExpirationDate(),
                    card.getCardPassword(),
                    card.getBalance(),
                    card.getCurrencyCode(),
                    card.getCardLimit(),
                    card.getCurrentLimit(),
                    card.getCreatAt(),
                    card.getCardStatus(),
                    card.getCardType()));
        }
        return cardResponseList;
    }

    @Override
    public Card updateCardLimits(Card card,UpdateCardLimitRequest updateCardLimitRequest) {
        card.setCardLimit(updateCardLimitRequest.cardLimit());
        card.setCurrentLimit(updateCardLimitRequest.currentLimit());
        return card;
    }

    @Override
    public Card updateCardPassword(Card card, UpdateCardPasswordRequest updateCardPasswordRequest) {
        card.setCardPassword(updateCardPasswordRequest.password());
        return card;
    }
}
