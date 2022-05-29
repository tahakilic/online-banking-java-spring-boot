package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.ShoppingWithCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.model.response.CreditCardDebtResponse;
import com.patikadev.onlinebanking.service.CardService;
import com.patikadev.onlinebanking.validator.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/cards")
public class CardController {
    private final CardService cardService;
    private final IDValidator idValidator;
    private final CreateCardRequestValidator createCardRequestValidator;
    private final UpdateCardLimitRequestValidator updateCardLimitRequestValidator;
    private final UpdateCardPasswordRequestValidator updateCardPasswordRequestValidator;
    private final CardNumberValidator cardNumberValidator;
    private final AmountValidator amountValidator;

    @PostMapping(path = "/customers/{customerId}")
    public ResponseEntity<String> createCard(@PathVariable Long customerId, @RequestBody CreateCardRequest createCardRequest) {
        idValidator.validate(customerId);
        cardNumberValidator.validate(createCardRequest.cardNumber());
        createCardRequestValidator.validate(createCardRequest);
        return ResponseEntity.ok(cardService.createCard(customerId, createCardRequest));
    }

    @GetMapping(path = "/{cardId}")
    public ResponseEntity<CardResponse> getCard(@PathVariable Long cardId) {
        idValidator.validate(cardId);
        return ResponseEntity.ok(cardService.getCard(cardId));
    }

    @GetMapping(path = "/customers/{customerId}")
    public ResponseEntity<List<CardResponse>> getCustomerAllCards(@PathVariable Long customerId) {
        idValidator.validate(customerId);
        return ResponseEntity.ok(cardService.getCustomerAllCards(customerId));
    }

    @PutMapping(path = "/{cardId}/limits")
    public ResponseEntity<String> updateCardLimits(@PathVariable Long cardId, @RequestBody UpdateCardLimitRequest updateCardLimitRequest) {
        idValidator.validate(cardId);
        updateCardLimitRequestValidator.validate(updateCardLimitRequest);
        return ResponseEntity.ok(cardService.updateCardLimits(cardId, updateCardLimitRequest));
    }

    @PutMapping(path = "/{cardId}/passwords")
    public ResponseEntity<String> updateCardPassword(@PathVariable Long cardId, @RequestBody UpdateCardPasswordRequest updateCardPasswordRequest) {
        idValidator.validate(cardId);
        updateCardPasswordRequestValidator.validate(updateCardPasswordRequest);
        return ResponseEntity.ok(cardService.updateCardPassword(cardId, updateCardPasswordRequest));
    }

    @DeleteMapping(path = "/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable Long cardId) {
        idValidator.validate(cardId);
        return ResponseEntity.ok(cardService.deleteCard(cardId));
    }

    @GetMapping
    public ResponseEntity<CreditCardDebtResponse> getCreditCardDebt(@RequestParam Long cardNumber) {
        cardNumberValidator.validate(cardNumber);
        return ResponseEntity.ok(cardService.getCreditCardDebt(cardNumber));
    }

    @PutMapping
    public ResponseEntity<String> atmPayToCard(@RequestParam Long cardNumber,
                                               @RequestParam BigDecimal amount) {
        amountValidator.validate(amount);
        cardNumberValidator.validate(cardNumber);
        return ResponseEntity.ok(cardService.atmPayToCard(amount, cardNumber));
    }

    @PutMapping(path = "/shopping")
    public ResponseEntity<?> shoppingWithCard(@RequestBody ShoppingWithCardRequest shoppingWithCardRequest) {
        amountValidator.validate(shoppingWithCardRequest.amount());
        cardNumberValidator.validate(shoppingWithCardRequest.cardNumber());

        return ResponseEntity.ok(cardService.shoppingWithCard(shoppingWithCardRequest));
    }


}
