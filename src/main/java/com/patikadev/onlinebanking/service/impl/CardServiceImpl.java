package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.CardConverter;
import com.patikadev.onlinebanking.exception.FacadeOperationException;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.exception.ValidationOperationException;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.enums.CardStatus;
import com.patikadev.onlinebanking.model.enums.CardType;
import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.ShoppingWithCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.model.response.CreditCardDebtResponse;
import com.patikadev.onlinebanking.repository.CardRepository;
import com.patikadev.onlinebanking.repository.CustomerRepository;
import com.patikadev.onlinebanking.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final CardConverter cardConverter;

    @Transactional
    @Override
    public String createCard(Long customerId, CreateCardRequest createCardRequest) {

        if (!(Objects.isNull(cardRepository.findByCardNumber(createCardRequest.cardNumber())))) {
            throw new ServiceOperationException.CardNotValidException("No more than one is created from the same cardNumber!");
        }
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        Card card = cardConverter.createCardRequestToCard(customer, createCardRequest);
        Card save = cardRepository.save(card);
        return save.getId() != null ? "successful" : "unsuccessful";
    }

    @Override
    public CardResponse getCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new ServiceOperationException.CardNotValidException("Card not found!"));
        return cardConverter.cardToCardResponse(card);
    }

    @Override
    public List<CardResponse> getCustomerAllCards(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        List<Card> cards = cardRepository.findByCustomer(customer);
        if (Objects.isNull(cards)) {
            throw new ServiceOperationException.CardNotValidException("Customer's card is not found!");
        }
        return cardConverter.cardListToCardResponseList(cards);
    }

    @Transactional
    @Override
    public String updateCardLimits(Long cardId, UpdateCardLimitRequest updateCardLimitRequest) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new ServiceOperationException.CardNotValidException("Card not found!"));
        if (card.getCardType() == CardType.BANK_CARD && !(Objects.isNull(updateCardLimitRequest.cardLimit()))) {
            throw new ServiceOperationException.CardNotValidException("Bank card cannot be cardLimit!");
        }
        if (card.getCardType() == CardType.CREDIT_CARD && Objects.isNull(updateCardLimitRequest.cardLimit())) {
            throw new ValidationOperationException.CardNotValidException(" 'cardLimit' can not be null or empty!");
        }
        Card newCard = cardConverter.updateCardLimits(card, updateCardLimitRequest);
        Card save = cardRepository.save(newCard);
        return save.getId() != null ? "successful" : "unsuccessful";
    }

    @Transactional
    @Override
    public String updateCardPassword(Long cardId, UpdateCardPasswordRequest updateCardPasswordRequest) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new ServiceOperationException.CardNotValidException("Card not found!"));
        Card newCard = cardConverter.updateCardPassword(card, updateCardPasswordRequest);
        Card save = cardRepository.save(newCard);
        return save.getId() != null ? "successful" : "unsuccessful";
    }

    @Transactional
    @Override
    public String deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Account not found!"));

        if (card.getBalance().compareTo(new BigDecimal(0)) != 0) {
            throw new ServiceOperationException.AccountBalanceNotEmpty("Card balance is not empty!");
        }
        String operation = "unsuccessful";
        if (card.getBalance().compareTo(new BigDecimal(0)) == 0) {
            cardRepository.deleteById(cardId);
            operation = "successful";
        }
        return operation;

    }

    @Override
    public CreditCardDebtResponse getCreditCardDebt(Long cardNumber) {
        Card creditCard = cardRepository.findByCardNumber(cardNumber);
        if (Objects.isNull(creditCard)) {
            throw new ServiceOperationException.CardNotValidException("Card number is not found!");
        }
        if (creditCard.getCardType() == CardType.BANK_CARD) {
            throw new ServiceOperationException.CardNotValidException("Bank card can not be debited!");
        }

        return cardConverter.cardToCreditCardDebtResponse(creditCard);
    }

    @Transactional
    @Override
    public String atmPayToCard(BigDecimal amount, Long cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber);
        if (Objects.isNull(card)) {
            throw new ServiceOperationException.CardNotValidException("Card not found!");
        }
        if (card.getCardStatus() != CardStatus.ACTIVE) {
            throw new FacadeOperationException.TransferNotValidException("Card is not active!");
        }
        if (card.getCardType() == CardType.CREDIT_CARD && card.getBalance().compareTo(amount) < 0) {
            if (card.getBalance().compareTo(new BigDecimal(0)) == 0) {
                throw new ServiceOperationException.CardNotValidException("Credit card debt is not found!");
            }
            BigDecimal remainder = amount.subtract(card.getBalance());
            Card newCard = cardConverter.atmPayToCard(amount, card);
            cardRepository.save(newCard);
            return "Remainder : " + remainder;
        }
        Card newCard = cardConverter.atmPayToCard(amount, card);
        Card save = cardRepository.save(newCard);
        return save.getId() != null ? "successful" : "unsuccessful";
    }

    @Transactional
    @Override
    public String shoppingWithCard(ShoppingWithCardRequest shoppingWithCardRequest) {
        Card card = cardRepository.findByCardNumber(shoppingWithCardRequest.cardNumber());
        if (Objects.isNull(card)) {
            throw new ServiceOperationException.CardNotValidException("Card not found!");
        }
        if (card.getCardStatus() != CardStatus.ACTIVE) {
            throw new ServiceOperationException.CardNotValidException("Card is not active!");
        }
        if (!(Objects.equals(card.getCardSecurityNumber(), shoppingWithCardRequest.cardSecurityNumber()))) {
            throw new ServiceOperationException.CardNotValidException("'cardSecurityNumber' (CCV) does not match!");
        }
        if (!(Objects.equals(card.getCardExpirationDate(), shoppingWithCardRequest.cardExpirationDate()))) {
            throw new ServiceOperationException.CardNotValidException("'cardExpirationDate' does not match!");
        }
        if (!(Objects.equals(card.getCardPassword(), shoppingWithCardRequest.cardPassword()))) {
            throw new ServiceOperationException.CardNotValidException("'password' does not match!");
        }
        if (card.getCardType() == CardType.BANK_CARD && card.getBalance().compareTo(shoppingWithCardRequest.amount()) < 0) {
            throw new ServiceOperationException.CardNotValidException("Insufficient balance!");
        }
        if (card.getCardType() == CardType.CREDIT_CARD && (card.getCardLimit().subtract(card.getBalance())).compareTo(shoppingWithCardRequest.amount()) < 0) {
            throw new ServiceOperationException.CardNotValidException("Insufficient balance!");
        }
        if (card.getCurrentLimit().compareTo(shoppingWithCardRequest.amount()) < 0) {
            throw new ServiceOperationException.CardNotValidException("'currentLimit' is overstep!");
        }
        Card newCard = cardConverter.shoppingWithCard(card, shoppingWithCardRequest);
        Card save = cardRepository.save(newCard);
        return save.getId() != null ? "successful" : "unsuccessful";
    }
}
