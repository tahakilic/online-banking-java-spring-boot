package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.request.TransferRequest;
import com.patikadev.onlinebanking.service.TransferService;
import com.patikadev.onlinebanking.validator.AmountValidator;
import com.patikadev.onlinebanking.validator.CardNumberValidator;
import com.patikadev.onlinebanking.validator.IbanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/transfers")
public class TransferController {
    private final AmountValidator amountValidator;
    private final IbanValidator ibanValidator;
    private final TransferService transferService;
    private final CardNumberValidator cardNumberValidator;

    @PostMapping
    public ResponseEntity<String> accountToAccount(@RequestParam String fromIban,
                                              @RequestParam String toIban,
                                              @RequestParam BigDecimal amount,
                                              @RequestBody TransferRequest transferRequest) {
        amountValidator.validate(amount);
        ibanValidator.validate(fromIban);
        ibanValidator.validate(toIban);

        return ResponseEntity.ok(transferService.accountToAccount(fromIban, toIban, transferRequest,amount));
    }


    @PostMapping(path = "/to/cards")
    public ResponseEntity<String> accountToCard(@RequestParam String fromAccountIban,
                                                @RequestParam Long toCardNumber,
                                                @RequestParam BigDecimal amount) {

        amountValidator.validate(amount);
        ibanValidator.validate(fromAccountIban);
        cardNumberValidator.validate(toCardNumber);
        return ResponseEntity.ok(transferService.accountToCard(amount, fromAccountIban, toCardNumber));
    }

    @PostMapping(path = "/to/accounts")
    public ResponseEntity<String> cardToAccount(@RequestParam Long fromCardNumber,
                                                @RequestParam String toAccountIban,
                                                @RequestParam BigDecimal amount) {
        cardNumberValidator.validate(fromCardNumber);
        ibanValidator.validate(toAccountIban);
        amountValidator.validate(amount);
        return ResponseEntity.ok(transferService.cardToAccount(fromCardNumber,toAccountIban,amount));
    }

}
