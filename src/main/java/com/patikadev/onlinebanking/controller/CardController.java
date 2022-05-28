package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.request.CreateCardRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardLimitRequest;
import com.patikadev.onlinebanking.model.request.UpdateCardPasswordRequest;
import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.service.CardService;
import com.patikadev.onlinebanking.validator.CreateCardRequestValidator;
import com.patikadev.onlinebanking.validator.IDValidator;
import com.patikadev.onlinebanking.validator.UpdateCardLimitRequestValidator;
import com.patikadev.onlinebanking.validator.UpdateCardPasswordRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/card")
public class CardController {
    private final CardService cardService;
    private final IDValidator idValidator;
    private final CreateCardRequestValidator createCardRequestValidator;
    private final UpdateCardLimitRequestValidator updateCardLimitRequestValidator;
    private final UpdateCardPasswordRequestValidator updateCardPasswordRequestValidator;

    @PostMapping(path = "/customers/{customerId}")
    public ResponseEntity<String> createCard(@PathVariable Long customerId, @RequestBody CreateCardRequest createCardRequest) {
        idValidator.validate(customerId);
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


}
