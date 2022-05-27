package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.response.CardResponse;
import com.patikadev.onlinebanking.validator.IDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/card")
public class CardController {
  /*  private final CardService cardService;
    private final IDValidator idValidator;

    @GetMapping(path = "/{cardId}")
    public ResponseEntity<CardResponse> getCard(@PathVariable Long cardId){
        idValidator.validate(cardId);
        return ResponseEntity.ok(cardService.getCard(cardId));
    }*/
}
