package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.CardConverter;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.repository.CardRepository;
import com.patikadev.onlinebanking.repository.CustomerRepository;
import com.patikadev.onlinebanking.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final CardConverter cardConverter;

    @Override
    public String createCard(Long customerId, CreateCardRequest createCardRequest) {

        if(!(Objects.isNull(cardRepository.findByCardNumber(createCardRequest.cardNumber())))){
            throw new ServiceOperationException.CardNotValidException("No more than one is created from the same cardNumber!");
        }
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        Card card = cardConverter.createCardRequestToCard(customer, createCardRequest);
        Card save = cardRepository.save(card);
        return save.getId() != null ?"successful":"unsuccessful";
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
        if (Objects.isNull(cards)){
            throw new ServiceOperationException.CardNotValidException("Customer's card is not found!");
        }
        return cardConverter.cardListToCardResponseList(cards);
    }

    @Override
    public String updateCardLimits(Long cardId,UpdateCardLimitRequest updateCardLimitRequest) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new ServiceOperationException.CardNotValidException("Card not found!"));
        Card newCard = cardConverter.updateCardLimits(card,updateCardLimitRequest);
        Card save = cardRepository.save(newCard);
        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Override
    public String updateCardPassword(Long cardId, UpdateCardPasswordRequest updateCardPasswordRequest) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new ServiceOperationException.CardNotValidException("Card not found!"));
        Card newCard = cardConverter.updateCardPassword(card,updateCardPasswordRequest);
        Card save = cardRepository.save(newCard);
        return save.getId() != null ?"successful":"unsuccessful";
    }
}
