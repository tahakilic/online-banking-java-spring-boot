package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.request.TransferRequest;
import com.patikadev.onlinebanking.service.TransferService;
import com.patikadev.onlinebanking.validator.AmountValidator;
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

    @PostMapping
    public ResponseEntity<?> accountToAccount(@RequestParam String fromIban,
                                              @RequestParam String toIban,
                                              @RequestBody TransferRequest transferRequest) {
        amountValidator.validate(transferRequest.amount());
        ibanValidator.validate(fromIban);
        ibanValidator.validate(toIban);

        return ResponseEntity.ok(transferService.accountToAccount(fromIban, toIban,transferRequest));
    }
}
