package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.CardConverter;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.enums.CardType;
import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.ShoppingWithCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.model.response.CreditCardDebtResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    public Card updateCardLimits(Card card, UpdateCardLimitRequest updateCardLimitRequest) {
        card.setCardLimit(updateCardLimitRequest.cardLimit());
        card.setCurrentLimit(updateCardLimitRequest.currentLimit());
        return card;
    }

    @Override
    public Card updateCardPassword(Card card, UpdateCardPasswordRequest updateCardPasswordRequest) {
        card.setCardPassword(updateCardPasswordRequest.password());
        return card;
    }

    @Override
    public CreditCardDebtResponse cardToCreditCardDebtResponse(Card creditCard) {
        return new CreditCardDebtResponse(creditCard.getCardNumber(),
                creditCard.getCardLimit().subtract(creditCard.getBalance()),
                creditCard.getBalance(),
                creditCard.getCardLimit());
    }

    @Override
    public Card atmPayToCard(BigDecimal amount, Card card) {
        if (card.getCardType() == CardType.CREDIT_CARD) {
            if (card.getBalance().compareTo(amount) < 0) {
                card.setBalance(new BigDecimal(0));
                return card;
            }
            card.setBalance(card.getBalance().subtract(amount));
            return card;
        }
        card.setBalance(card.getBalance().add(amount));
        return card;
    }

    @Override
    public Card shoppingWithCard(Card card, ShoppingWithCardRequest shoppingWithCardRequest) {
        if(card.getCardType()==CardType.CREDIT_CARD){
            card.setBalance(card.getBalance().add(shoppingWithCardRequest.amount()));
            return card;
        }
        card.setBalance(card.getBalance().subtract(shoppingWithCardRequest.amount()));
        return card;
    }
}
